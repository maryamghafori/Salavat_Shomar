package ir.ghafouri.salavatshomar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ir.ghafouri.salavatshomar.R;
import ir.ghafouri.salavatshomar.Objects.DrawerObject;

public class DrawerAdapter extends ArrayAdapter <DrawerObject> {

    private List<DrawerObject> objects;

    public DrawerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_drowabel_row, parent , false);

        TextView name        = view.findViewById(R.id.NumberLy);
        TextView description = view.findViewById(R.id.DateLy);

        name.setText(objects.get(position).getName());
        description.setText(objects.get(position).getDescription());


        return view;
    }
}
