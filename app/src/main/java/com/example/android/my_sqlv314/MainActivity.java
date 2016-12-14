package com.example.android.my_sqlv314;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.ResultSetMetaData;

import java.sql.DriverManager;
//test
public class MainActivity extends AppCompatActivity  {

    private static final String url = "jdbc:mysql://192.168.1.33:3306/test";
    private static final String user = "admin";
    private static final String pass = "admin";

    //** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testDB();
    }

    public void testDB() {
        TextView tv = (TextView)this.findViewById(R.id.text_view);
        try {

            StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con =  DriverManager.getConnection(url, user, pass);
        /* System.out.println("Database connection success"); */

            String result = "Database connection success\n";
            java.sql.Statement st =  con.createStatement();
            ResultSet rs = (ResultSet) st.executeQuery("select * from test");
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

            while(rs.next()) {
                result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
                result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
                result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
            }
            tv.setText(result);
        }
        catch(Exception e) {
            e.printStackTrace();
            tv.setText(e.toString());
        }

    }
}
