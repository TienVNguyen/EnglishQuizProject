/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.englishquiz;

import java.util.ArrayList;

/**
 * SubmitObject
 *
 * @author TienNguyen
 */
public class SubmitObject {
    private String name;
    private String email;
    private ArrayList<String> jobs;
    private boolean quest1Correct;
    private boolean quest2Correct;
    private boolean quest3Correct;
    private boolean quest4Correct;
    private boolean quest5Correct;
    private boolean quest6Correct;

    public SubmitObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<String> jobs) {
        this.jobs = jobs;
    }

    public boolean isQuest1Correct() {
        return quest1Correct;
    }

    public void setQuest1Correct(boolean quest1Correct) {
        this.quest1Correct = quest1Correct;
    }

    public boolean isQuest2Correct() {
        return quest2Correct;
    }

    public void setQuest2Correct(boolean quest2Correct) {
        this.quest2Correct = quest2Correct;
    }

    public boolean isQuest3Correct() {
        return quest3Correct;
    }

    public void setQuest3Correct(boolean quest3Correct) {
        this.quest3Correct = quest3Correct;
    }

    public boolean isQuest4Correct() {
        return quest4Correct;
    }

    public void setQuest4Correct(boolean quest4Correct) {
        this.quest4Correct = quest4Correct;
    }

    public boolean isQuest5Correct() {
        return quest5Correct;
    }

    public void setQuest5Correct(boolean quest5Correct) {
        this.quest5Correct = quest5Correct;
    }

    public boolean isQuest6Correct() {
        return quest6Correct;
    }

    public void setQuest6Correct(boolean quest6Correct) {
        this.quest6Correct = quest6Correct;
    }
}
