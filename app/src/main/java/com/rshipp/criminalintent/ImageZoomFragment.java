package com.rshipp.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Image zoom dialog
 */
public class ImageZoomFragment extends DialogFragment {
    public static final String EXTRA_IMAGE =
            "com.rshipp.criminalintent.image";
    private static final String ARG_IMAGE = "image";


    private ImageView mImageView;

    public static ImageZoomFragment newInstance(File imageFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE, imageFile);

        ImageZoomFragment fragment = new ImageZoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        File imageFile = (File) getArguments().getSerializable(ARG_IMAGE);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_image, null);

        mImageView = (ImageView) v.findViewById(R.id.dialog_image_image_view);
        updatePhotoView(imageFile);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.image_zoom_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_OK);
                            }
                        }).create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        //intent.putExtra(EXTRA_IMAGE, image);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    private void updatePhotoView(File imageFile) {
        if (imageFile == null || !imageFile.exists()) {
            mImageView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    imageFile.getPath(), getActivity());
            mImageView.setImageBitmap(bitmap);
        }
    }


}
