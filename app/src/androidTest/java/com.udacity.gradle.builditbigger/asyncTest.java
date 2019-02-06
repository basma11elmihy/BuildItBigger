package com.udacity.gradle.builditbigger;
import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertNotNull;

//http://marksunghunpark.blogspot.com/2015/05/how-to-test-asynctask-in-android.html

@RunWith(AndroidJUnit4.class)
public class asyncTest {
    String testResultString = null;
    @Test
    public void testTask(){

        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.setListner(new EndpointsAsyncTask.taskListener() {
            @Override
            public void onComplete(String resultString) {
                if (!resultString.equals(""))
                testResultString = resultString;

            }
        }).execute();
        try{
            Thread.sleep(10000);
            assertNotNull(testResultString);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
