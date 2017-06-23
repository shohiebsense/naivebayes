package com.shohiebsense.myapplication.controllers;

import android.util.Log;

import com.shohiebsense.myapplication.models.Contact;
import com.shohiebsense.myapplication.models.Human;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * Created by Shohieb on 5/13/2017.
 */

public class HumanController {


    Realm realm;
    public HumanController init(){
        realm = Realm.getDefaultInstance();
        return this;
    }

    public String random(String prefix){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int randomLength = random.nextInt(7);
        char tempChar;
        for(int i =0;i<randomLength;i++){
            tempChar = (char) (random.nextInt(96) + 32);
            sb.append(prefix + tempChar);
        }
        return sb.toString();
    }

    public Contact addContact(int id){
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(random("nama "));
        contact.setEmail(random("email :"));
        contact.setPhone(random("phone : "));
        return contact;
    }

    public Human addHuman(int id,String name, String gender){
        Human human = new Human();
        human.setId(id);
        human.setName(name);
        human.setGender(gender);
        if(gender.equalsIgnoreCase("laki2")){
            double height = getRandomDouble();
            human.setHeight(height);
            if(height < 174){
                human.setClassification("pendek");
            }
            else {
                human.setClassification("tinggi");
            }
        }
        else{
            double height = getRandomDoubleForWomen();
            human.setHeight(height);
            if(height < 160){
                human.setClassification("pendek");
            }
            else{
                human.setClassification("tinggi");
            }
        }
        return human;
    }

    public String randomName(String prefix){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int randomLength = random.nextInt(7);
        char tempChar;
        for(int i =0;i<randomLength;i++){
            tempChar = (char) (random.nextInt(96) + 32);
            sb.append(prefix + tempChar);
        }
        return sb.toString();
    }


    public double getRandomDouble(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        Random r = new Random();
        Number n=  150 + (200 - 150) * r.nextDouble();
        return Double.parseDouble(df.format(n.doubleValue()));
    }

    public double getRandomDoubleForWomen(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        Random r = new Random();
        Number n=  150 + (170 - 150) * r.nextDouble();
        return Double.parseDouble(df.format(n.doubleValue()));
    }

    public int getRandomIntWithinRange(int first, int last){
        Random r = new Random();
        return r.nextInt(2) + 1;
    }

    public void addContacts(){
        realm.beginTransaction();
        realm.where(Contact.class).findAll().deleteAllFromRealm();
        for(int i = 0; i<5;i++){
            Number id = realm.where(Contact.class).max("id");
            if(id == null){
                id = 1;
            }
            else{
                id = id.intValue() + 1;
            }
            realm.copyToRealm(addContact(id.intValue()));
        }
        realm.commitTransaction();
    }

    public String generateGender(int number){
        Log.e("shohiebsense ",number+"");
        if(number == 1){
            return "perempuan";
        }
        return "laki2";
    }

    public void addHumans(){
        realm.beginTransaction();
        realm.where(Human.class).findAll().deleteAllFromRealm();
        realm.where(Contact.class).findAll().deleteAllFromRealm();

        for(int i = 0; i<10;i++){
            Number id = realm.where(Human.class).max("id");
            int number = getRandomIntWithinRange(0,1);
            String name = "Insan "+i+1;
            if(id == null){
                id = 1;
            }
            else{
                id = id.intValue() + 1;
            }
            String gender = generateGender(number);
            if(gender.equalsIgnoreCase("laki2")){

            }
            else{

            }
            Log.e("shohiebsense ",""+gender);
            realm.copyToRealm(addHuman(id.intValue(),name,gender));
        }
        realm.commitTransaction();
    }

    public RealmResults<Contact> getAllContacts(){
        return realm.where(Contact.class).findAll();
    }

    public RealmResults<Human> getAllHumans() { return realm.where(Human.class).findAll();}

    public double hitungProbLakiPendek(){
        return realm.where(Human.class).equalTo("gender","laki2").lessThanOrEqualTo("height",174).count();
    }

    public double hitungProbLakiTinggi(){
        return realm.where(Human.class).equalTo("gender","laki2").greaterThan("height",174).count();
    }

   /* public double hitungPerempuanTinggi(){
        return realm.where(Human.class).equalTo("gender","perempuan").greaterThan("height",160).count();
    }

    public double hitungPerempuanPendek(){
        return realm.where(Human.class).equalTo("gender","perempuan").lessThanOrEqualTo("height",160).count();
    }*/

    public RealmResults<Human> hitungLakiPendek(){
        return realm.where(Human.class).equalTo("gender","laki2").equalTo("classification","pendek").findAll();
    }

    public RealmResults<Human> hitungLakiTinggi(){
        return realm.where(Human.class).equalTo("gender","laki2").equalTo("classification","tinggi").findAll();
    }

    public RealmResults<Human> hitungPerempuanPendek(){
        return realm.where(Human.class).equalTo("gender","perempuan").equalTo("classification","pendek").findAll();
    }

    public RealmResults<Human> hitungPerempuanTinggi(){
        return realm.where(Human.class).equalTo("gender","perempuan").equalTo("classification","tinggi").findAll();
    }

    public static double rata2(RealmResults<Human> orang){
        double sum = 0;
        for(int i =0; i< orang.size(); i++){
            sum += orang.get(i).getHeight();
        }
        return sum / orang.size();

    }

    public static double getVariance(RealmResults<Human> orang){
        double mean = rata2(orang);
        double temp = 0;
        for(int i =0; i< orang.size(); i++){
            temp += Math.pow(orang.get(i).getHeight() - mean,2);
        }
        return temp / (orang.size() -1);
    }

    public static double getStandarDev(RealmResults<Human> tinggi){
        return Math.sqrt(getVariance(tinggi));
    }

    public static double normalDistribution(RealmResults<Human> tinggi, double x){
        if(tinggi == null){
            return new NormalDistribution(86.2,9.7).cumulativeProbability(74);
        }
        double a = new NormalDistribution(rata2(tinggi),getStandarDev(tinggi)).cumulativeProbability(x);
        return a;
    }

    public static String getRounded(double rounded){
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(rounded);
    }


}
