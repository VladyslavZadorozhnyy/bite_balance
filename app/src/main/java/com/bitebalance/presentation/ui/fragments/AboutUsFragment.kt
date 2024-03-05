package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.bitebalance.databinding.FragmentTextScreenBinding

class AboutUsFragment : BaseFragment<FragmentTextScreenBinding>() {

    override fun onStartFragment(): View {
        binding = FragmentTextScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainerConstraint)

        return binding.root
    }

    override fun setupViewModelsObservation() {
        themeVm.state.observe(this) {
            setupHeader()
            setupSubtext()
        }
    }

    private fun setupHeader() {
        binding.root.setBackgroundColor(secondaryColor)

        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.about_us),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
    }

    private fun setupSubtext() {
        binding.subtext.backgroundTintList = ColorStateList.valueOf(secondaryColor)

        binding.subtext.setup(
            model = TextModel(
                textValue = getString(R.string.project_info),
                textSize = Constants.TEXT_SIZE,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
    }

    companion object {
        fun newInstance(): AboutUsFragment {
            return AboutUsFragment()
        }
    }
}