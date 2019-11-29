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

from flask import  Flask  #pip3 install Flask
from flask.json import jsonify
import json
from flask import request
app = Flask(__name__)

def calculateTwitterUsers(eventName, tweets):
    listOfTwittNames = []
    count = 0;
    for eachTwitt in tweets:
        doc = nlp(eachTwitt)
        for X in doc.ents:
            print(X.text, X.label_)
            if lemmatizer.lemmatize(X.text) == 'India' or 'India' in X.text or 'India' in lemmatizer.lemmatize(X.text):
                count = count+1
                continue
    
    return jsonify(count)

@app.route('/count/<ticketMasterEvent>',methods=['POST'])
def count(ticketMasterEvent):

    print(ticketMasterEvent)
    twitter_dict = request.get_json()
    print(twitter_dict)
    
    event = 'India v Pakistan'
    tweets =['India vs Pakistan match','India vs Pakistan','Pakistan v India']
    
    #return calculateTwitterUsers(ticketMasterEvent, twitter_dict)
    return calculateTwitterUsers(event, tweets)

@app.route('/home')
def index():

  appDict = {
   'name': 'messenger',
   'playstore': True,
   'company': 'Facebook',
   'price': 100
  }
  app_json = json.dumps(appDict)
  return jsonify(app_json)

   app.run(debug=True)