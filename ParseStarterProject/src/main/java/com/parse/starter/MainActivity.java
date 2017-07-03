/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    /*
      this is how to save object using parse server
    // create a ParseObject score with in class Score
    ParseObject score = new ParseObject("Score");
    // add some data into parse Object
    score.put("username","Anchal");
    score.put("score",86);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {

          if(e == null){

            Log.i("SaveInBackground","Successful");
          } else{

              Log.i("SaveInBackground","Failed. Error: "+ e.toString());
          }
      }
    });*/

    /* // Bring data from parse server

      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
         query.getInBackground("SFQaU4UXfV", new GetCallback<ParseObject>() {
             @Override
             public void done(ParseObject object, ParseException e) {
                 if(e == null && object!=null){

                     object.put("score",200);
                     object.saveInBackground();
                     Log.i("ObjectValue",object.getString("username"));
                     Log.i("ObjectValue",Integer.toString(object.getInt("score")));

                 }
             }
         });*/

     // find from query

      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

      query.whereEqualTo("username","Anchal");

      query.setLimit(1);

       query.findInBackground(new FindCallback<ParseObject>() {
           @Override
           public void done(List<ParseObject> objects, ParseException e) {

               if( e==null ){
                   Log.i("findInBackground","Retrieved" + objects.size()+" objects");

                   if(objects.size()>0){

                       for(ParseObject object : objects){


                           Log.i("findResults",object.getString("username"));

                       }

                   }
               }
           }
       });



    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}
