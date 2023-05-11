package es.ifp.easycount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MachineListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference machinesRef;
    private MachineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MachineAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        machinesRef = FirebaseDatabase.getInstance().getReference().child("machines");

        machinesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Machine> machineList = new ArrayList<>();

                for (DataSnapshot machineSnapshot : dataSnapshot.getChildren()) {
                    Machine machine = machineSnapshot.getValue(Machine.class);
                    machineList.add(machine);
                }

                adapter.setMachineList(machineList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Database", "Error retrieving data from database. " + databaseError.getMessage());
            }
        });
    }
}
