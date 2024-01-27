package com.dev.service.implementation;

import java.util.Optional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import com.dev.model.message.Message;
import com.dev.model.user.User;
import com.dev.repository.MessageRepository;
import com.dev.repository.UserRepository;
import com.dev.service.UserService;
import com.dev.utils.TokenUtils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public User loginUser(User user) {
        String mail = user.getMail();
        String mdp = user.getMotdepasse();
        // test statique
        ArrayList<User> data = statiqueData();
        for(User u : data){
            if( (u.getMail().equalsIgnoreCase(mail)==true) && (u.getMotdepasse().equalsIgnoreCase(mdp)==true) ){
                return u;
            }
        }
        return null;
    }

    // Ajout donnée statique 
    public static ArrayList<User> statiqueData(){
        ArrayList<User> data = new ArrayList<>();
        // data.add(new User(1,"Jean","Doe","mail1","pass1"));
        // data.add(new User(2,"Marc","Doe","mail2","pass2"));
        return data;
    }

    @Override
    public Message envoyerMessage(User userSend, User userReceive, String contenu, Timestamp dateTime)
    throws Exception {
        Message message=new Message(null, userSend, userReceive, contenu, 1, new Date());
        messageRepository.save(message);
        return message;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByMail(email);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

	@Override
	public List<Message> findByUserSendAndUserReceive(User userSend, User userReceive) {
        Criteria criteria = new Criteria().orOperator(
            Criteria.where("userSend").is(userSend).and("userReceive").is(userReceive),
            Criteria.where("userSend").is(userReceive).and("userReceive").is(userSend)
        );

        Query query = new Query(criteria).with(Sort.by(Sort.Order.asc("dateHeureMessage")));

        return mongoTemplate.find(query, Message.class);
        // return messageRepository.findByUserSendAndUserReceiveOrUserReceiveAndUserSendOrderByDateHeureMessage(userSend, userReceive, userReceive, userSend);
	}

    @Override
    public List<User> findDistinctUsersForUser(User user) {
        Query query = new Query(Criteria.where("userSend").is(user));
        List<Message> userSendList = mongoTemplate.find(query, Message.class);
        Query query2 = new Query(Criteria.where("userReceive").is(user));
        List<Message> userReceive = mongoTemplate.find(query, Message.class);
        HashMap<Integer, User> users=new HashMap<>();
        List<User> result=new ArrayList<>();
        for(int i=0; i<userSendList.size(); i++) {
            if(users.get(userSendList.get(i).getUserReceive().getId())==null) {
                users.put(userSendList.get(i).getUserReceive().getId(), userSendList.get(i).getUserReceive());
                result.add(userSendList.get(i).getUserReceive());
            }
        }
        for(int i=0; i<userReceive.size(); i++) {
            if(users.get(userReceive.get(i).getUserSend().getId())==null) {
                users.put(userReceive.get(i).getUserSend().getId(), userReceive.get(i).getUserSend());
                result.add(userReceive.get(i).getUserSend());
            }
        }
        return result;
    }
}
