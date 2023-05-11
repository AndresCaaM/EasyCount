package es.ifp.easycount;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private EditText locationEditText, statusEditText, financeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true); // Optional: Enable offline persistence
        databaseReference = database.getReferenceFromUrl("https://easycount-c663b-default-rtdb.europe-west1.firebasedatabase.app/");

        // Initialize EditText fields
        locationEditText = findViewById(R.id.locationEditText);
        statusEditText = findViewById(R.id.statusEditText);
        financeEditText = findViewById(R.id.financeEditText);

        // Find the "View Database" button
        Button viewDatabaseButton = findViewById(R.id.viewButton);

        // Set the OnClickListener for the "View Database" button
        viewDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDatabase();
            }
        });
    }

    public void saveData(View view) {
        String location = locationEditText.getText().toString();
        String status = statusEditText.getText().toString();
        String finance = financeEditText.getText().toString();

        // Save the data to the Firebase database
        String key = databaseReference.child("machines").push().getKey();
        Machine machine = new Machine(location, status, finance);
        databaseReference.child("machines").child(key).setValue(machine);

        // Clear the EditText fields
        locationEditText.setText("");
        statusEditText.setText("");
        financeEditText.setText("");

        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
    }

    private void viewDatabase() {
        Intent intent = new Intent(MainActivity.this, MachineListActivity.class);
        startActivity(intent);
    }



    // Model class for Machine
    private static class Machine {
        public String location;
        public String status;
        public String finance;

        public Machine(String location, String status, String finance) {
            this.location = location;
            this.status = status;
            this.finance = finance;
        }
    }
}