package com.example.components.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.components.R
import com.example.components.buttons.common.ButtonModel
import com.example.components.checkbox.CheckBoxModel
import com.example.components.databinding.FragmentDemonstrationBinding
import com.example.components.dialogs.common.BaseDialogModel
import com.example.components.dialogs.confirm_dialog.ConfirmDialog
import com.example.components.dialogs.input_dialog.InputDialog
import com.example.components.dialogs.yes_no_dialog.YesNoDialog
import com.example.components.input_form.InputFormModel
import com.example.components.mocks.MockNutritionModel
import com.example.components.nav_bar.NavigationBarModel
import com.example.components.progress.carousel.ProgressCarouselModel
import com.example.components.texts.common.TextModel

// TODO: remove this fragment, put all fragments to com.example.layouts.fragments package
class DemonstrationFragment : Fragment() {
    private val binding by lazy { FragmentDemonstrationBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTitle()
        setupTextSample()
        setupSlideableTextSample()
        setupTextIconButtonSample()
        setupTextButtonSample()
        setupIconButtonSample()
        setupCarousel()
        setupNavigation()
        setupCheckBox()
        setupInputForm()



        binding.confirmDialogButton.setOnClickListener {
            ConfirmDialog(
                activity = requireActivity(),
                model = BaseDialogModel(
                    backgroundColorRes = R.color.white,
                    textColor = Color.BLACK,
                    title = "Confirm dialog"
                )
            ).show()
        }

        binding.yesNoDialogButton.setOnClickListener {
            YesNoDialog(
                activity = requireActivity(),
                model = BaseDialogModel(
                    backgroundColorRes = R.color.white,
                    textColor = Color.BLACK,
                    title = "Yes/No dialog",
                    onPositiveClicked = { Log.d("AAADIP", "onPositive clicked") },
                    onNegativeClicked = { Log.d("AAADIP", "onNegative clicked") }
                )
            ).show()
        }

        binding.inputDialogButton.setOnClickListener {
            Log.d("AAADIP", "confirmDialogButton called")
            InputDialog(
                activity = requireActivity(),
                model = BaseDialogModel(
                    backgroundColorRes = R.color.black,
                    textColor = Color.WHITE,
                    title = "My next goal is to :",
                    onInputConfirmed = { Log.d("AAADIP", "confirmButton clicked: $it") },
                )
            ).show()
        }
    }

    private fun setupTitle() {
        binding.title.setup(
            model = TextModel(
                textValue = "Demonstration Fragment",
                textColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
        )
    }

    private fun setupTextSample() {
        binding.textSample.setup(
            model = TextModel(
                textValue = "Text sample component",
                textColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
        )
    }

    private fun setupSlideableTextSample() {
        binding.slideableTextSample.setup(
            model = TextModel(
                textValue = "Very very long text sample component",
                textColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
        )
    }

    private fun setupTextIconButtonSample() {
        binding.textIconButtonSample.setup(
            model = ButtonModel(
                iconRes = R.drawable.info_icon,
                iconSize = 80,
                labelTextSize = 14,
                labelTextRes = R.string.text_icon_button_sample,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"TextIconButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )
    }

    private fun setupTextButtonSample() {
        binding.textButtonSample.setup(
            model = ButtonModel(
                labelTextSize = 20,
                labelTextRes = R.string.text_button_sample,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"TextButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )
    }

    private fun setupIconButtonSample() {
        binding.iconButtonSample.setup(
            model = ButtonModel(
                iconRes = R.drawable.info_icon,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"IconButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )
    }

    private fun setupCarousel() {
        binding.progressCarousel.setup(model = ProgressCarouselModel(
            consumed = MockNutritionModel(
                fat = 10F,
                carb = 14F,
                ccal = 750F,
                protein = 10F,
            ),
            goalConsumption = MockNutritionModel(
                fat = 10F,
                carb = 12F,
                ccal = 2000F,
                protein = 10F,
            )
        ))
    }

    private fun setupNavigation() {
        binding.navigationComponent.setup(
            NavigationBarModel(
                nonActiveIconsRes = listOf(
                    R.drawable.nav_home_not_active,
                    R.drawable.nav_stats_not_active,
                    R.drawable.nav_menu_not_active,
                    R.drawable.nav_settings_not_active
                ),
                activeIconsRes = listOf(
                    R.drawable.nav_home_active,
                    R.drawable.nav_stats_active,
                    R.drawable.nav_menu_active,
                    R.drawable.nav_settings_active
                ),
            ) {
                Log.d("AAADIP", "menu item chosen it: $it")
            }
        )
    }

    private fun setupCheckBox() {
        binding.checkboxText.setup(
            TextModel(
                textValue = "Checkbox text",
                textSize = 20,
                textColor = R.color.black,
                backgroundColor = Color.TRANSPARENT,
            )
        )

        binding.checkboxComponent.setup(
            model = CheckBoxModel(
                checked = false,
                active = true,
                onChecked = { binding.checkboxText.strikeThrough() },
                onUnchecked = { binding.checkboxText.unstrikeThrough() }
            )
        )
    }

    private fun setupInputForm() {
        binding.inputForm.setup(
            model = InputFormModel(
                active = true,
                onInputChange = { Log.d("AAADIP", "onInputChange called with: $it") }
            )
        )
    }
}