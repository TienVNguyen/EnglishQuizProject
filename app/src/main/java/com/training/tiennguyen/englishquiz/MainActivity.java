/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.englishquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MainActivity
 *
 * @author TienNguyen
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.img_banner)
    protected ImageView imgBanner;
    @Bind(R.id.img_animal)
    protected ImageView imgAnimal;
    @Bind(R.id.edt_name)
    protected EditText edtName;
    @Bind(R.id.edt_email)
    protected EditText edtEmail;
    @Bind(R.id.ckb_student)
    protected CheckBox ckbStudent;
    @Bind(R.id.ckb_worker)
    protected CheckBox ckbWorker;
    @Bind(R.id.ckb_developer)
    protected CheckBox ckbDeveloper;
    @Bind(R.id.ckb_engineer)
    protected CheckBox ckbEngineer;
    @Bind(R.id.rdb_question_1_answers)
    protected RadioGroup rdbQuestion1Answers;
    @Bind(R.id.rdb_question_2_answers)
    protected RadioGroup rdbQuestion2Answers;
    @Bind(R.id.rdb_question_3_answers)
    protected RadioGroup rdbQuestion3Answers;
    @Bind(R.id.rdb_question_4_answers)
    protected RadioGroup rdbQuestion4Answers;
    @Bind(R.id.rdb_question_5_answers)
    protected RadioGroup rdbQuestion5Answers;
    @Bind(R.id.edt_question_6)
    protected EditText edtQuestion6;
    @Bind(R.id.btn_submit)
    protected Button btnSubmit;
    @Bind(R.id.btn_reset)
    protected Button btnReset;

    private SubmitObject submitObject;

    /**
     * This action will be executed when activity is called
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * Initial view(s) inside of main activity
     */
    private void initView() {
        ButterKnife.bind(this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFunction();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFunction();
            }
        });

        Picasso.with(MainActivity.this)
                .load(getString(R.string.banner_value))
                .resize(400, 400)
                .centerCrop()
                .into(imgBanner);

        Picasso.with(MainActivity.this)
                .load(getString(R.string.img_question_6))
                .resize(400, 400)
                .centerCrop().into(imgAnimal);

        edtName.requestFocus();
    }

    /**
     * Submit
     */
    private void submitFunction() {
        if (validateInput()) {
            final StringBuilder message = new StringBuilder();
            message.append(getString(R.string.txt_name));
            message.append(submitObject.getName());
            message.append("\n");
            message.append(getString(R.string.txt_email));
            message.append(submitObject.getEmail());
            message.append("\n");
            if (submitObject.getJobs() != null) {
                message.append(getString(R.string.txt_job));
                message.append(submitObject.getJobs().toString());
                message.append("\n");
            }
            message.append(getString(R.string.submit_question, "1"));
            message.append(submitObject.isQuest1Correct());
            message.append("\n");
            message.append(getString(R.string.submit_question, "2"));
            message.append(submitObject.isQuest2Correct());
            message.append("\n");
            message.append(getString(R.string.submit_question, "3"));
            message.append(submitObject.isQuest3Correct());
            message.append("\n");
            message.append(getString(R.string.submit_question, "4"));
            message.append(submitObject.isQuest4Correct());
            message.append("\n");
            message.append(getString(R.string.submit_question, "5"));
            message.append(submitObject.isQuest5Correct());
            message.append("\n");
            message.append(getString(R.string.submit_question, "6"));
            message.append(submitObject.isQuest6Correct());

            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            break;
                    }
                }
            };

            AlertDialog.Builder builderDialog = new AlertDialog.Builder(MainActivity.this);
            builderDialog.setIcon(android.R.drawable.ic_dialog_info);
            builderDialog.setTitle(getString(R.string.submit_title));
            builderDialog.setPositiveButton(getString(R.string.submit_email), onClickListener);
            builderDialog.setMessage(message);
            builderDialog.show();
        }
    }

    /**
     * Validate before submit.<br>
     * If everything is fine, the SubmitObject will be filled.
     *
     * @return boolean
     */
    private boolean validateInput() {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(MainActivity.this);
        builderDialog.setIcon(android.R.drawable.ic_dialog_alert);
        builderDialog.setTitle(getString(R.string.error));
        builderDialog.setPositiveButton(getString(R.string.again), onClickListener);

        submitObject = new SubmitObject();
        Object validateEditText = edtName.getText();
        if (validateEditText == null || validateEditText.toString().trim().isEmpty()) {
            edtName.requestFocus();
            builderDialog.setMessage(getString(R.string.error_name));
            builderDialog.show();
            return false;
        } else {
            submitObject.setName(validateEditText.toString().trim());
        }

        validateEditText = edtEmail.getText();
        if (validateEditText == null || validateEditText.toString().trim().isEmpty()) {
            edtEmail.requestFocus();
            builderDialog.setMessage(getString(R.string.error_email));
            builderDialog.show();
            return false;
        } else if (!isValidEmail(validateEditText.toString().trim())) {
            edtEmail.requestFocus();
            builderDialog.setMessage(getString(R.string.error_invalid_email));
            builderDialog.show();
            return false;
        } else {
            submitObject.setEmail(validateEditText.toString().trim());
        }

        ArrayList<String> jobs = new ArrayList<>();
        if (ckbStudent.isChecked()) {
            jobs.add(ckbStudent.getText().toString());
        }
        if (ckbWorker.isChecked()) {
            jobs.add(ckbWorker.getText().toString());
        }
        if (ckbDeveloper.isChecked()) {
            jobs.add(ckbDeveloper.getText().toString());
        }
        if (ckbEngineer.isChecked()) {
            jobs.add(ckbEngineer.getText().toString());
        }
        if (jobs.size() > 0) {
            submitObject.setJobs(jobs);
        }

        RadioButton radioButton1 = (RadioButton) rdbQuestion1Answers.getChildAt(0);
        RadioButton radioButton2 = (RadioButton) rdbQuestion1Answers.getChildAt(1);
        RadioButton radioButton3 = (RadioButton) rdbQuestion1Answers.getChildAt(2);
        boolean validateRadioButtonA = radioButton1.isChecked();
        boolean validateRadioButtonB = radioButton2.isChecked();
        boolean validateRadioButtonC = radioButton3.isChecked();
        if (!validateRadioButtonA & !validateRadioButtonB & !validateRadioButtonC) {
            radioButton1.requestFocus();
            radioButton1.setFocusableInTouchMode(true);
            builderDialog.setMessage(getString(R.string.error_rdb_question, "1"));
            builderDialog.show();
            return false;
        } else {
            // Correct answer is B
            if (validateRadioButtonB) {
                submitObject.setQuest1Correct(true);
            } else {
                submitObject.setQuest1Correct(false);
            }
        }

        radioButton1 = (RadioButton) rdbQuestion2Answers.getChildAt(0);
        radioButton2 = (RadioButton) rdbQuestion2Answers.getChildAt(1);
        radioButton3 = (RadioButton) rdbQuestion2Answers.getChildAt(2);
        validateRadioButtonA = radioButton1.isChecked();
        validateRadioButtonB = radioButton2.isChecked();
        validateRadioButtonC = radioButton3.isChecked();
        if (!validateRadioButtonA & !validateRadioButtonB & !validateRadioButtonC) {
            radioButton1.requestFocus();
            radioButton1.setFocusableInTouchMode(true);
            builderDialog.setMessage(getString(R.string.error_rdb_question, "2"));
            builderDialog.show();
            return false;
        } else {
            // Correct answer is C
            if (validateRadioButtonC) {
                submitObject.setQuest2Correct(true);
            } else {
                submitObject.setQuest2Correct(false);
            }
        }

        radioButton1 = (RadioButton) rdbQuestion3Answers.getChildAt(0);
        radioButton2 = (RadioButton) rdbQuestion3Answers.getChildAt(1);
        radioButton3 = (RadioButton) rdbQuestion3Answers.getChildAt(2);
        validateRadioButtonA = radioButton1.isChecked();
        validateRadioButtonB = radioButton2.isChecked();
        validateRadioButtonC = radioButton3.isChecked();
        if (!validateRadioButtonA & !validateRadioButtonB & !validateRadioButtonC) {
            radioButton1.requestFocus();
            radioButton1.setFocusableInTouchMode(true);
            builderDialog.setMessage(getString(R.string.error_rdb_question, "3"));
            builderDialog.show();
            return false;
        } else {
            // Correct answer is B
            if (validateRadioButtonB) {
                submitObject.setQuest3Correct(true);
            } else {
                submitObject.setQuest3Correct(false);
            }
        }

        radioButton1 = (RadioButton) rdbQuestion4Answers.getChildAt(0);
        radioButton2 = (RadioButton) rdbQuestion4Answers.getChildAt(1);
        radioButton3 = (RadioButton) rdbQuestion4Answers.getChildAt(2);
        validateRadioButtonA = radioButton1.isChecked();
        validateRadioButtonB = radioButton2.isChecked();
        validateRadioButtonC = radioButton3.isChecked();
        if (!validateRadioButtonA & !validateRadioButtonB & !validateRadioButtonC) {
            radioButton1.requestFocus();
            radioButton1.setFocusableInTouchMode(true);
            builderDialog.setMessage(getString(R.string.error_rdb_question, "4"));
            builderDialog.show();
            return false;
        } else {
            // Correct answer is B
            if (validateRadioButtonB) {
                submitObject.setQuest4Correct(true);
            } else {
                submitObject.setQuest4Correct(false);
            }
        }

        radioButton1 = (RadioButton) rdbQuestion5Answers.getChildAt(0);
        radioButton2 = (RadioButton) rdbQuestion5Answers.getChildAt(1);
        radioButton3 = (RadioButton) rdbQuestion5Answers.getChildAt(2);
        validateRadioButtonA = radioButton1.isChecked();
        validateRadioButtonB = radioButton2.isChecked();
        validateRadioButtonC = radioButton3.isChecked();
        if (!validateRadioButtonA & !validateRadioButtonB & !validateRadioButtonC) {
            radioButton1.requestFocus();
            radioButton1.setFocusableInTouchMode(true);
            builderDialog.setMessage(getString(R.string.error_rdb_question, "5"));
            builderDialog.show();
            return false;
        } else {
            // Correct answer is A
            if (validateRadioButtonA) {
                submitObject.setQuest5Correct(true);
            } else {
                submitObject.setQuest5Correct(false);
            }
        }

        validateEditText = edtQuestion6.getText();
        if (validateEditText == null || validateEditText.toString().trim().isEmpty()) {
            edtQuestion6.requestFocus();
            builderDialog.setMessage(getString(R.string.error_question6));
            builderDialog.show();
            return false;
        } else if (validateEditText.toString().trim().equalsIgnoreCase("dog") || validateEditText.toString().trim().equalsIgnoreCase("dogs")) {
            // Correct answer is "dog" or "dogs"
            submitObject.setQuest6Correct(true);
        } else {
            submitObject.setQuest6Correct(false);
        }

        return true;
    }

    /**
     * Validate for email.
     *
     * @param target CharSequence
     * @return boolean
     */
    private static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Reset all
     */
    private void resetFunction() {
        edtName.requestFocus();
        edtName.setText("");
        edtEmail.setText("");
        ckbStudent.setChecked(false);
        ckbWorker.setChecked(false);
        ckbDeveloper.setChecked(false);
        ckbEngineer.setChecked(false);
        rdbQuestion1Answers.clearCheck();
        rdbQuestion2Answers.clearCheck();
        rdbQuestion3Answers.clearCheck();
        rdbQuestion4Answers.clearCheck();
        rdbQuestion5Answers.clearCheck();
        edtQuestion6.setText("");

        Toast.makeText(this, getString(R.string.btn_reset), Toast.LENGTH_SHORT).show();
    }
}
