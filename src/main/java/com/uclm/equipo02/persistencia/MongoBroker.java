package com.uclm.equipo02.persistencia;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoBroker {
	private static MongoBroker yo;
	private MongoClientURI uri;
	private MongoClient mongoClient;
	private static MongoDatabase db;

	private MongoBroker(){
		this.uri= new MongoClientURI("mongodb://equipo02:equipo02gps@ds115740.mlab.com:15740/fichajes");
		this.mongoClient= new MongoClient(uri);
		this.db=mongoClient.getDatabase(uri.getDatabase());
	}

	public static MongoBroker get(){
		if (yo==null){
			yo = new MongoBroker();
		}
		return yo;
	}

	public static MongoCollection<Document> getCollection (String collection){
		MongoCollection <Document> result=db.getCollection(collection, Document.class);
	
		if(result==null){
			db.createCollection(collection);
			result=db.getCollection(collection,Document.class);
		}

		return result;
	}
	
	public void insertDoc(MongoCollection<Document> colection, Document documento) {
		colection.insertOne(documento);
	}
	
	public void updateDoc(MongoCollection<Document> colection, Document filtro, Document documento) {
		colection.updateOne(filtro, documento);
}
}