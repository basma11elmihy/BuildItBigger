package com.example.android.javalib;


import java.util.Random;

public class JavaJoke {
    private String theJoke[] = {"the most amazing joke, LOL!","Knock! Knock!\n" +
            "\n" +
            "Who's there?\n" +
            "\n" +
            "Figs.\n" +
            "\n" +
            "Figs who?\n" +
            "\n" +
            "Figs the doorbell, it's broken!","Q. Which U.S. state is famous for its extra-small soft drinks?\n" +
            "\n" +
            "A. Mini-soda!","Don't use \"beef stew\" as a computer password. It's not stroganoff.\n" +
            "\n","My girlfriend was complaining last night that I never listen to her. Or something like that...\n" +
            "\n","Q. How are stars like false teeth?\n" +
            "\n" +
            "A. They both come out at night!\n" +
            "\n","Q. Why do skunks love Valentine's Day?\n" +
            "\n" +
            "A. Because they're scent-imental creatures!","Q. What do you get when you cross a centipede with a parrot?\n" +
            "\n" +
            "A. A walkie-talkie!"};

    public String getTheJoke() {
        int randomJoke = new Random().nextInt(theJoke.length);
        return theJoke[randomJoke];
    }
}
