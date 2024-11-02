package com.systemDesign.designPatterns.SingletonPattern;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

    }
}

/**
 * useful in an application for creating DB connection := so only 1 db connection is formed
 * the issue with this class & impl is - it is not safe in case of a multithreading
 * to solve this issue := we can use EagerInitialization technique; meaning create the instance when JVM is loaded and then return the same instance
 */
class SingletonPattern {
    private static SingletonPattern instance = null;

    private SingletonPattern() {
    }

    public static SingletonPattern getInstance(){
        if (instance == null) instance = new SingletonPattern();
        return instance;
    }
}

/**
 * the issue here is the class is created when JVM loads; so an object will definitely be created
 * now if this obj is not used, the resources are wasted
 * the solution might be
 */
class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton(); //initialized when JVM loads

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance(){
        return instance;
    }
}

/**
 * the solution to above problem can be to make the initialization inside a synchronized block
 * however this faces an issue of serialization
 */
class MultiThreadSingleton{
    private static MultiThreadSingleton instance = null;

    private MultiThreadSingleton(){}

    public static MultiThreadSingleton getInstance(){
        if(instance == null){
            synchronized (MultiThreadSingleton.class){
                if(instance == null){ //check for the thread that has entered the sync block
                    instance = new MultiThreadSingleton();
                }
            }
        }
        return instance;
    }
}

class SerializableSingleton implements Serializable{
    private static SerializableSingleton instance = null;

    private SerializableSingleton(){}

    public static SerializableSingleton getInstance(){
        if(instance == null){
            synchronized (MultiThreadSingleton.class){
                if(instance == null){ //check for the thread that has entered the sync block
                    instance = new SerializableSingleton();
                }
            }
        }
        return instance;
    }
}

