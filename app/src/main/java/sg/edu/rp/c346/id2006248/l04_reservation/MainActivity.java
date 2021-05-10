package sg.edu.rp.c346.id2006248.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etNumber;
    EditText etSize;
    DatePicker dp;
    TimePicker tp;
    CheckBox cbSmoking;
    Button btSubmit;
    Button btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etNumber = findViewById(R.id.editTextPhone);
        etSize = findViewById(R.id.editTextSize);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        cbSmoking = findViewById(R.id.checkBoxSmoking);
        btSubmit = findViewById(R.id.buttonSubmit);
        btReset = findViewById(R.id.buttonReset);

        dp.updateDate(2021,5,1);
        tp.setCurrentMinute(30);
        tp.setCurrentHour(19);

        Calendar cal = Calendar.getInstance();

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((etName.getText().toString().trim().length() == 0) || (etNumber.getText().toString().trim().length() == 0) || (etSize.getText().toString().trim().length() == 0)) {
                    Toast.makeText(MainActivity.this, "Reservation Failed", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this,"Input must not be empty!",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Reservation Successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Name: " + etName.getText(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Number: " + etNumber.getText(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Size: " + etSize.getText(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Date is " + dp.getDayOfMonth() + "/" + dp.getMonth() + "/" + dp.getYear(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Time is " + tp.getCurrentHour() + ":" + tp.getCurrentMinute(), Toast.LENGTH_SHORT).show();

                    if (cbSmoking.isChecked()) {
                        Toast.makeText(MainActivity.this, "Area: Smoking", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Area: Non-Smoking", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 8){
                    tp.setCurrentHour(8);
                }
                else if(hourOfDay > 20){
                    tp.setCurrentHour(20);
                }
            }
        });

        dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int currentYear = cal.get(Calendar.YEAR);
                int currentMonth = cal.get(Calendar.MONTH);
                int currentDay = cal.get(Calendar.DAY_OF_MONTH);

                if(year < currentYear){
                    dp.updateDate(currentYear,currentMonth,currentDay + 1);
                }
                else if(monthOfYear < currentMonth){
                    dp.updateDate(currentYear,currentMonth,currentDay + 1);
                }
                else if((monthOfYear == currentMonth) && (dayOfMonth <= currentDay)){
                    dp.updateDate(currentYear,currentMonth,currentDay + 1);
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etNumber.setText("");
                etSize.setText("");
                dp.updateDate(2021,5,1);
                tp.setCurrentMinute(30);
                tp.setCurrentHour(19);
                cbSmoking.setChecked(false);
            }
        });
    }
}