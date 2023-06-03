// Generated by view binder compiler. Do not edit!
package com.ui.components.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.ui.components.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TextButtonBinding implements ViewBinding {
  @NonNull
  private final View rootView;

  @NonNull
  public final MaterialButton buttonView;

  private TextButtonBinding(@NonNull View rootView, @NonNull MaterialButton buttonView) {
    this.rootView = rootView;
    this.buttonView = buttonView;
  }

  @Override
  @NonNull
  public View getRoot() {
    return rootView;
  }

  @NonNull
  public static TextButtonBinding inflate(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    if (parent == null) {
      throw new NullPointerException("parent");
    }
    inflater.inflate(R.layout.text_button, parent);
    return bind(parent);
  }

  @NonNull
  public static TextButtonBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_view;
      MaterialButton buttonView = ViewBindings.findChildViewById(rootView, id);
      if (buttonView == null) {
        break missingId;
      }

      return new TextButtonBinding(rootView, buttonView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}