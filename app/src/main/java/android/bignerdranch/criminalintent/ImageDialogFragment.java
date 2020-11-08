package android.bignerdranch.criminalintent;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class ImageDialogFragment extends DialogFragment {
    private static final String ARG_PHOTO = "photo";
    private ImageView mPhoto;

    public static ImageDialogFragment newInstance(Crime crime) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, crime.getPhotoFilename());
        ImageDialogFragment fragment = new ImageDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String crimePhotoName = (String) Objects.requireNonNull(getArguments()).getSerializable(ARG_PHOTO);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photo, null);
        mPhoto = v.findViewById(R.id.dialog_photo_image_view);
        mPhoto.setImageBitmap(PictureUtils.getScaledBitmap(CrimeLab.get(getActivity()).getPhotoFile(crimePhotoName).getPath(), getActivity()));
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setCancelable(true)
                .create();
    }
}
