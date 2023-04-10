package com.bunyamin.hw5_bunyaminozturk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private int amountInt;
    private TextView tvShowAmount;
    private EditText inputName;
    private TextView tvName;
    private TextView tvAddWhipped;
    private TextView tvAddChocolate;
    private TextView tvTotalNum;
    private TextView tvQuantity;
    private CheckBox check_box_whipped;
    private CheckBox check_box_chocolate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        Toast toastZeroLess = Toast.makeText(getApplicationContext(), "The amount cannot be 0 or less.", Toast.LENGTH_LONG);
        Toast toastUnchecked = Toast.makeText(getApplicationContext(), "You need to choose 1 or more items.", Toast.LENGTH_LONG);
        Toast toastNoName = Toast.makeText(getApplicationContext(), "You need to type a name", Toast.LENGTH_LONG);
        Toast toastZeroQuantity = Toast.makeText(getApplicationContext(), "The amount cannot be 0 or less.", Toast.LENGTH_LONG);

        Button btn_plus;
        Button btn_minus;
        Button btn_order;


        tvShowAmount = findViewById(R.id.tv_amount_int);
        tvShowAmount.setText(String.valueOf(amountInt));

        inputName = findViewById(R.id.et_name);


        tvName = findViewById(R.id.tv_edit_name);

        check_box_whipped = findViewById(R.id.whipped_checkbox);
        tvAddWhipped = findViewById(R.id.tv_add_whipped_cream_boolean);

        check_box_chocolate = findViewById(R.id.chocolate_checkbox);
        tvAddChocolate = findViewById(R.id.tv_add_chocolate_boolean);

        tvQuantity = findViewById(R.id.tv_quantity_num);


        tvTotalNum = findViewById(R.id.tv_total_num);



        btn_plus = findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(v -> {
            countUp();
            showAmount();
        });
        btn_minus = findViewById(R.id.btn_minus);
        btn_minus.setOnClickListener(v -> {
            if (amountInt <= 0) {
                toastZeroLess.show();
            } else {
                countDown();
                showAmount();
            }

        });

        btn_order = findViewById(R.id.btn_order);
        btn_order.setOnClickListener(v -> {
            boolean bolItems = check_box_chocolate.isChecked() || check_box_whipped.isChecked();
            boolean bolLengthName = inputName.getText().length() > 0;
            boolean bolAmount = amountInt > 0;


            if (!check_box_chocolate.isChecked() & !check_box_whipped.isChecked()) {
                toastUnchecked.show();
                lyGone();

            } if (inputName.getText().length() <= 0) {
                toastNoName.show();
                lyGone();
            } if (amountInt <= 0) {
                toastZeroQuantity.show();
                lyGone();
            } if(bolAmount && bolItems && bolLengthName)  {

                showName();
                showWhippedCream();
                showChocolate();
                showQuantity();
                goneName();
                lyVisible();
                showTotal();
            }

        });

    }

    private void showName() {
        String nameStr = inputName.getText().toString();
        tvName.setText(nameStr);
    }

    private void showChocolate() {
        tvAddChocolate.setText(String.valueOf(check_box_chocolate.isChecked()));
    }

    private void showWhippedCream() {
        tvAddWhipped.setText(String.valueOf(check_box_whipped.isChecked()));
    }

    private void showQuantity() {
        tvQuantity.setText(String.valueOf(amountInt));
    }

    private void countUp() {
        amountInt++;
    }

    private void countDown() {
        amountInt--;
    }

    private void showAmount() {
        tvShowAmount.setText(String.valueOf(amountInt));
    }

    private void lyVisible() {
        View lyAmounts = findViewById(R.id.ly_outputs);
        lyAmounts.setVisibility(View.VISIBLE);
    }

    private void goneName() {
        View editName = findViewById(R.id.et_name);
        editName.setVisibility(View.GONE);
    }

    private void lyGone() {
        View lyAmounts = findViewById(R.id.ly_outputs);
        lyAmounts.setVisibility(View.GONE);
    }

    private void showTotal() {
        tvTotalNum.setText("$" + Total());
    }

    private int Total() {
        int whippedCreamInt, chocolateInt, totalInt;
        int creamPrice = 5;
        int chocolatePrice = 7;
        if (check_box_whipped.isChecked()) {
            whippedCreamInt = 1;
        } else {
            whippedCreamInt = 0;
        }
        if (check_box_chocolate.isChecked()) {
            chocolateInt = 1;
        } else {
            chocolateInt = 0;
        }

        totalInt = amountInt * ((creamPrice * whippedCreamInt)
                + (chocolatePrice * chocolateInt));

        return totalInt;
    }
}