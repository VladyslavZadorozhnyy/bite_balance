// Generated by view binder compiler. Do not edit!
package com.ui.components.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ui.basic.buttons.icon_button.IconButton;
import com.ui.basic.texts.text.Text;
import com.ui.components.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MealViewholderBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final IconButton clockIcon;

  @NonNull
  public final IconButton deleteButtonIcon;

  @NonNull
  public final IconButton mealIcon;

  @NonNull
  public final Text mealTextView;

  @NonNull
  public final Text timeTextView;

  private MealViewholderBinding(@NonNull LinearLayout rootView, @NonNull IconButton clockIcon,
      @NonNull IconButton deleteButtonIcon, @NonNull IconButton mealIcon,
      @NonNull Text mealTextView, @NonNull Text timeTextView) {
    this.rootView = rootView;
    this.clockIcon = clockIcon;
    this.deleteButtonIcon = deleteButtonIcon;
    this.mealIcon = mealIcon;
    this.mealTextView = mealTextView;
    this.timeTextView = timeTextView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MealViewholderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MealViewholderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.meal_viewholder, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MealViewholderBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.clock_icon;
      IconButton clockIcon = ViewBindings.findChildViewById(rootView, id);
      if (clockIcon == null) {
        break missingId;
      }

      id = R.id.delete_button_icon;
      IconButton deleteButtonIcon = ViewBindings.findChildViewById(rootView, id);
      if (deleteButtonIcon == null) {
        break missingId;
      }

      id = R.id.meal_icon;
      IconButton mealIcon = ViewBindings.findChildViewById(rootView, id);
      if (mealIcon == null) {
        break missingId;
      }

      id = R.id.meal_text_view;
      Text mealTextView = ViewBindings.findChildViewById(rootView, id);
      if (mealTextView == null) {
        break missingId;
      }

      id = R.id.time_text_view;
      Text timeTextView = ViewBindings.findChildViewById(rootView, id);
      if (timeTextView == null) {
        break missingId;
      }

      return new MealViewholderBinding((LinearLayout) rootView, clockIcon, deleteButtonIcon,
          mealIcon, mealTextView, timeTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
