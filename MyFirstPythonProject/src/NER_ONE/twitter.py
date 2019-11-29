import json
import nltk
import spacy
import en_core_web_sm
from nltk.stem import WordNetLemmatizer 
from spacy import displacy
from collections import Counter
from operator import or_
nlp = en_core_web_sm.load()

lemmatizer = WordNetLemmatizer() 

with open('D:/jsonparser/jsonParser/fullFledgedTM.json', encoding="utf-8", errors='ignore') as f:
    events_dict = json.load(f)
    print(events_dict)
listOfEventNames = []
for eachEvent in events_dict['_embedded']['events']:
    eventName = eachEvent['name']
    #print(eventName)
    listOfEventNames.append(eventName)
    
#print(listOfEventNames)


with open('D:/jsonparser/jsonParser/fullFledgedTwitter.json', encoding="utf-8", errors='ignore') as f:
    twitter_dict = json.load(f)
    print(twitter_dict)
listOfTwittNames = []
count = 0;
for eachTwitt in twitter_dict['tweets']:
    twittText = eachTwitt['full_text']
    #print(twittText)

    listOfTwittNames.append(twittText)
    doc = nlp(twittText)
    
    #print([(X.text, X.label_) for X in doc.ents])
    
    for X in doc.ents:
        #print(X.text, X.label_)
        if lemmatizer.lemmatize(X.text) == 'Eagle' or 'Eagle' in X.text or 'Eagle' in lemmatizer.lemmatize(X.text):
            count = count+1
            continue
            
    #print([(X.text, X.label_) for X in doc.ents])
    
#print(listOfTwittNames)
print(count)
    