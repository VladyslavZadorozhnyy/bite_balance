// Generated by view binder compiler. Do not edit!
package com.ui.components.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.ui.basic.texts.text.Text;
import com.ui.components.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class TextViewholderBinding implements ViewBinding {
  @NonNull
  private final Text rootView;

  @NonNull
  public final Text textView;

  private TextViewholderBinding(@NonNull Text rootView, @NonNull Text textView) {
    this.rootView = rootView;
    this.textView = textView;
  }

  @Override
  @NonNull
  public Text getRoot() {
    return rootView;
  }

  @NonNull
  public static TextViewholderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TextViewholderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.text_viewholder, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TextViewholderBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    Text textView = (Text) rootView;

    return new TextViewholderBinding((Text) rootView, textView);
  }
}
