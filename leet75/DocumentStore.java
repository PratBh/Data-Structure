package leet75;

import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


class Document {
    String documentName;
    Map<LocalTime,String> content;
    public Document(String documentName,String content){
        this.documentName=documentName;
        this.content = new TreeMap<LocalTime, String>(Collections.reverseOrder());
        this.content.put(LocalTime.now(),content);
    }
    
    public String loadFromTime(String documentName,LocalTime t){
        if(content.containsKey(t))
            return content.get(t);
        else{
            for(LocalTime t1:content.keySet()){
                if(t.compareTo(t1)>0)
                    return content.get(t1);
            }
        }
        return null;
    }
}

class DocumentStore {
    Map<String,Document> store;
    public DocumentStore(){
        this.store = new HashMap<>();
    }
    
    public void save(String documentName,String text){
        if(store.containsKey(documentName)){
            Document doc = store.get(documentName);
            doc.content.put(LocalTime.now(), text);
        }else {
        	Document doc = new Document(documentName,text);
        	store.put(documentName,doc);
        }
    }
    
    public String load(String documentName){
        if(!store.containsKey(documentName))
            return "";
        Document doc = store.get(documentName);
        return doc.loadFromTime(documentName, LocalTime.now());
    }
    
    public String loadFromTime(String documentName,LocalTime t){
        if(!store.containsKey(documentName))
            return "";
        Document doc = store.get(documentName);
        return doc.loadFromTime(documentName, t);
    }
}