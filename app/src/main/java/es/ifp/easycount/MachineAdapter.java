package es.ifp.easycount;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.ViewHolder> {

    private List<Machine> machineList;

    public MachineAdapter(List<Machine> machineList) {
        this.machineList = machineList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView locationTextView;
        public TextView statusTextView;
        public TextView financeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            financeTextView = itemView.findViewById(R.id.financeTextView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.machine_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Machine machine = machineList.get(position);

        holder.locationTextView.setText(machine.getLocation());
        holder.statusTextView.setText(machine.getStatus());
        holder.financeTextView.setText(machine.getFinance());
    }

    @Override
    public int getItemCount() {
        return machineList.size();
    }

    public void setMachineList(List<Machine> machineList) {
        this.machineList = machineList;
        notifyDataSetChanged();
    }
}
