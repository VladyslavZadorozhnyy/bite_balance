//package com.ui.mocks
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.core.content.ContextCompat.getColorStateList
//import androidx.fragment.app.Fragment
//import com.ui.basic.buttons.common.ButtonModel
//import com.ui.basic.checkbox.CheckBoxModel
//import com.ui.basic.input_form.InputFormModel
//import com.ui.basic.nav_bar.NavigationBarModel
//import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel
//import com.ui.basic.recycler_views.goal_recycler.GoalRecyclerModel
//import com.ui.basic.recycler_views.meal_recycler.MealRecyclerModel
//import com.ui.basic.recycler_views.metric_recycler.MetricRecyclerModel
//import com.ui.basic.texts.common.TextModel
//import com.ui.components.R
//import com.ui.components.databinding.FragmentDemonstrationBinding
//import com.ui.components.dialogs.common.BaseDialogModel
//import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
//import com.ui.components.dialogs.input_dialog.InputDialog
//import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
//import com.ui.components.graph.component.GraphModel
//import com.ui.components.progress.carousel.ProgressCarouselModel
//
//
//// TODO: remove this fragment, put all fragments to com.example.layouts.fragments package
//class DemonstrationFragment : Fragment() {
//    private val binding by lazy {
//        FragmentDemonstrationBinding.inflate(layoutInflater)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setupTitle()
//        setupTextSample()
//        setupSlideableTextSample()
//        setupTextIconButtonSample()
//        setupTextButtonSample()
//        setupIconButtonSample()
//        setupCarousel()
//        setupNavigation()
//        setupCheckBox()
//        setupInputForm()
//        setupDialogs()
//        setupChartButton()
//        setupGoalRecycler()
//        setupMealRecycler()
//        setupDishRecycler()
//        setupMetricRecycler()
//
//        binding.getInputValue.setup(
//            model = ButtonModel(
//                iconRes = R.drawable.info_icon,
//                iconSize = 80,
//                labelTextSize = 14,
//                labelTextRes = R.string.get_input_button,
//                foregroundColorRes = R.color.white,
//                backgroundColorRes = R.color.black,
//                onClickListener =  {
//                    Log.d("AAADIP", "getValue called")
//                    Log.d("AAADIP", "getInputValues: ${binding.metricRecycler.getInputValues()}")
//                }
//            )
//        )
//    }
//
//    private fun setupTitle() {
//        binding.title.setup(
//            model = TextModel(
//                textValue = "Demonstration Fragment",
//            )
//        )
//    }
//
//    private fun setupTextSample() {
//        binding.textSample.setup(
//            model = TextModel(
//                textValue = "Text sample component",
//            )
//        )
//    }
//
//    private fun setupSlideableTextSample() {
//        binding.slideableTextSample.setup(
//            model = TextModel(
//                textValue = "Very very long text sample component",
//            )
//        )
//    }
//
//    private fun setupTextIconButtonSample() {
//        binding.textIconButtonSample.setup(
//            model = ButtonModel(
//                iconRes = R.drawable.info_icon,
//                iconSize = 80,
//                labelTextSize = 14,
//                labelTextRes = R.string.text_icon_button_sample,
//                foregroundColorRes = R.color.white,
//                backgroundColorRes = R.color.black,
//                onClickListener =  {
//                    Toast.makeText(activity,"TextIconButton clicked!", Toast.LENGTH_SHORT).show()
//                }
//            )
//        )
//    }
//
//    private fun setupTextButtonSample() {
//        binding.textButtonSample.setup(
//            model = ButtonModel(
//                labelTextSize = 20,
//                labelTextRes = R.string.text_button_sample,
//                foregroundColorRes = R.color.white,
//                backgroundColorRes = R.color.black,
//                onClickListener =  {
//                    Toast.makeText(activity,"TextButton clicked!", Toast.LENGTH_SHORT).show()
//                }
//            )
//        )
//    }
//
//    private fun setupIconButtonSample() {
//        binding.iconButtonSample.setup(
//            model = ButtonModel(
//                iconRes = R.drawable.info_icon,
//                iconSize = 100,
//                foregroundColorRes = R.color.white,
//                backgroundColorRes = R.color.black,
//                onClickListener =  {
//                    Toast.makeText(activity,"IconButton clicked!", Toast.LENGTH_SHORT).show()
//                }
//            )
//        )
//    }
//
//    private fun setupCarousel() {
//        binding.progressCarousel.setup(
//            model = ProgressCarouselModel(
//                consumed = MockNutritionModel(
//                    fat = 10F,
//                    carb = 14F,
//                    kcal = 750F,
//                    protein = 10F,
//                ),
//                goalConsumption = MockNutritionModel(
//                    fat = 10F,
//                    carb = 12F,
//                    kcal = 2000F,
//                    protein = 10F,
//                )
//            )
//        )
//    }
//
//    private fun setupNavigation() {
//        binding.navigationComponent.setup(
//            NavigationBarModel(
//                nonActiveIconsRes = listOf(
//                    R.drawable.nav_home_not_active,
//                    R.drawable.nav_stats_not_active,
//                    R.drawable.nav_menu_not_active,
//                    R.drawable.nav_settings_not_active
//                ),
//                activeIconsRes = listOf(
//                    R.drawable.nav_home_active,
//                    R.drawable.nav_stats_active,
//                    R.drawable.nav_menu_active,
//                    R.drawable.nav_settings_active
//                ),
//            ) {
//                Log.d("AAADIP", "menu item chosen it: $it")
//            }
//        )
//    }
//
//    private fun setupCheckBox() {
//        binding.checkboxText.setup(
//            TextModel(
//                textValue = "Checkbox text",
//                textSize = 20,
//                textColorRes = R.color.black,
//                backgroundColor = R.color.transparent,
//            )
//        )
//
//        binding.checkboxComponent.setup(
//            model = CheckBoxModel(
//                checked = false,
//                active = true,
//                onChecked = { binding.checkboxText.strikeThrough() },
//                onUnchecked = { binding.checkboxText.unstrikeThrough() }
//            )
//        )
//    }
//
//    private fun setupInputForm() {
//        binding.inputForm.setup(
//            model = InputFormModel(
//                active = true,
//                onInputChange = { Log.d("AAADIP", "onInputChange called with: $it") }
//            )
//        )
//    }
//
//    private fun setupDialogs() {
//        binding.confirmDialogButton.setOnClickListener {
//            ConfirmDialog(
//                activity = requireActivity(),
//                model = BaseDialogModel(
//                    backgroundColorRes = R.color.white,
//                    textColorRes = R.color.black,
//                    title = "Confirm dialog"
//                )
//            ).show()
//        }
//
//        binding.yesNoDialogButton.setOnClickListener {
//            YesNoDialog(
//                activity = requireActivity(),
//                model = BaseDialogModel(
//                    backgroundColorRes = R.color.white,
//                    textColorRes = R.color.black,
//                    title = "Yes/No dialog",
//                    onPositiveClicked = { Log.d("AAADIP", "onPositive clicked") },
//                    onNegativeClicked = { Log.d("AAADIP", "onNegative clicked") }
//                )
//            ).show()
//        }
//
//        binding.inputDialogButton.setOnClickListener {
//            Log.d("AAADIP", "confirmDialogButton called")
//            InputDialog(
//                activity = requireActivity(),
//                model = BaseDialogModel(
//                    backgroundColorRes = R.color.black,
//                    textColorRes = R.color.white,
//                    title = "My next goal is to :",
//                    onInputConfirmed = { Log.d("AAADIP", "confirmButton clicked: $it") },
//                )
//            ).show()
//        }
//    }
//
//    private fun setupChartButton() {
//        binding.chartButton.setOnClickListener {
//            binding.chart.setup(
//                GraphModel(
//                    consumption = listOf(
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            2000F
//                        ),
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            1900F
//                        ),
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            1800F
//                        ),
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            1700F
//                        ),
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            1600F
//                        ),
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            1500F
//                        ),
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            1900F
//                        ),
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            1900F
//                        ),
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            1900F
//                        ),
//                        MockNutritionModel(
//                            10F,
//                            20F,
//                            15F,
//                            1900F
//                        )
//                    ),
//                    consumptionGoal = MockNutritionModel(
//                        10F,
//                        40F,
//                        8F,
//                        1500F
//                    ),
//                    screenSpan = 7
//                )
//            )
//        }
//    }
//
//    private fun setupGoalRecycler() {
//        binding.goalRecycler.setup(
//            GoalRecyclerModel(
//                items = listOf(
//                    MockGoalModel(textValue = "One", active = true, achieved = true),
//                    MockGoalModel(textValue = "Two", active = false, achieved = true),
//                    MockGoalModel(textValue = "Three", active = false, achieved = false),
//                    MockGoalModel(textValue = "Four", active = false, achieved = false),
//                    MockGoalModel(textValue = "Five", active = true, achieved = false)
//                ),
//                backgroundColorRes = R.color.black
//            )
//        )
//    }
//
//    private fun setupMealRecycler() {
//        binding.mealRecycler.setup(
//            model = MealRecyclerModel(
//                items = listOf(
//                    MockMealModel(
//                        mealTime = "08:30",
//                        dish = MockDishModel(
//                            name = "dish 1",
//                            iconRes = R.drawable.dinner_icon,
//                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                        ),
//                        amount = 100
//                    ),
//                    MockMealModel(
//                        mealTime = "10:30",
//                        dish = MockDishModel(
//                            name = "dish 2",
//                            iconRes = R.drawable.dinner_icon,
//                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                        ),
//                        amount = 100
//                    ),
//                    MockMealModel(
//                        mealTime = "12:30",
//                        dish = MockDishModel(
//                            name = "dish 3",
//                            iconRes = R.drawable.dinner_icon,
//                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                        ),
//                        amount = 100
//                    ),
//                    MockMealModel(
//                        mealTime = "14:30",
//                        dish = MockDishModel(
//                            name = "dish 4",
//                            iconRes = R.drawable.dinner_icon,
//                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                        ),
//                        amount = 100
//                    ),
//                    MockMealModel(
//                        mealTime = "18:30",
//                        dish = MockDishModel(
//                            name = "dish 5",
//                            iconRes = R.drawable.dinner_icon,
//                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                        ),
//                        amount = 100
//                    ),
//                ),
//                backgroundColorRes = R.color.black,
//                onClickListener = {}
//            )
//        )
//    }
//
//    private fun setupDishRecycler() {
//        binding.dishRecycler.backgroundTintList = getColorStateList(requireContext(), R.color.white)
//        binding.dishRecycler.setup(
//            model = DishRecyclerModel(
//                items = listOf(
//                    MockDishModel(
//                        name = "Dish 1",
//                        iconRes = R.drawable.breakfast_icon,
//                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                    ),
//                    MockDishModel(
//                        name = "Dish 2",
//                        iconRes = R.drawable.breakfast_icon,
//                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                    ),
//                    MockDishModel(
//                        name = "Dish 3",
//                        iconRes = R.drawable.breakfast_icon,
//                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                    ),
//                    MockDishModel(
//                        name = "Dish 4",
//                        iconRes = R.drawable.breakfast_icon,
//                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                    ),
//                    MockDishModel(
//                        name = "Dish 5",
//                        iconRes = R.drawable.breakfast_icon,
//                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                    ),
//                    MockDishModel(
//                        name = "Dish 6",
//                        iconRes = R.drawable.breakfast_icon,
//                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
//                    )
//                ),
//                onClickListener = {}
//            )
//        )
//    }
//
//    private fun setupMetricRecycler() {
//        binding.metricRecycler.setup(
//            MetricRecyclerModel(
//                items = listOf(
//                    MockMetricModel(
//                        name = "Name:",
//                        editable = true
//                    ),
//                    MockMetricModel(
//                        name = "Prots:",
//                        hint = "value",
//                        suffix = "g in 100g"
//                    ),
//                    MockMetricModel(
//                        name = "Fats:",
//                        hint = "value",
//                        suffix = "g in 100g"
//                    ),
//                    MockMetricModel(
//                        name = "Carbs:",
//                        hint = "value",
//                        suffix = "g in 100g"
//                    ),
//                    MockMetricModel(
//                        name = "Kcal:",
//                        hint = "value",
//                        suffix = "kcal in 100g"
//                    ),
//                    MockMetricModel(
//                        name = "Eaten:",
//                        hint = "value",
//                        suffix = "in g"
//                    )
//                )
//            )
//        )
//
//        binding.metricRecycler.backgroundTintList = getColorStateList(requireContext(), R.color.white)
//    }
//}