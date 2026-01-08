package com.example.ui.ext

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nutapos.nutatest.core.ui.R


inline val View.success_7_color get() = context.success_7_color
inline val Context.success_7_color: Int
    get() = ContextCompat.getColor(this, R.color.success_7_color)

inline val View.success_6_color get() = context.success_6_color
inline val Context.success_6_color: Int
    get() = ContextCompat.getColor(this, R.color.success_6_color)

inline val View.success_3_color get() = context.success_3_color
inline val Context.success_3_color: Int
    get() = ContextCompat.getColor(this, R.color.success_3_color)

inline val View.success_1_color get() = context.success_1_color
inline val Context.success_1_color: Int
    get() = ContextCompat.getColor(this, R.color.success_1_color)

inline val View.warning_7_color get() = context.warning_7_color
inline val Context.warning_7_color: Int
    get() = ContextCompat.getColor(this, R.color.warning_7_color)

inline val View.warning_6_color get() = context.warning_6_color
inline val Context.warning_6_color: Int
    get() = ContextCompat.getColor(this, R.color.warning_6_color)

inline val View.warning_3_color get() = context.warning_3_color
inline val Context.warning_3_color: Int
    get() = ContextCompat.getColor(this, R.color.warning_3_color)

inline val View.warning_1_color get() = context.warning_1_color
inline val Context.warning_1_color: Int
    get() = ContextCompat.getColor(this, R.color.warning_1_color)

inline val View.danger_7_color get() = context.danger_7_color
inline val Context.danger_7_color: Int
    get() = ContextCompat.getColor(this, R.color.danger_7_color)

inline val View.danger_6_color get() = context.danger_6_color
inline val Context.danger_6_color: Int
    get() = ContextCompat.getColor(this, R.color.danger_6_color)

inline val View.danger_3_color get() = context.danger_3_color
inline val Context.danger_3_color: Int
    get() = ContextCompat.getColor(this, R.color.danger_3_color)

inline val View.text_5_color get() = context.text_5_color
inline val Context.text_5_color: Int
    get() = ContextCompat.getColor(this, R.color.text_5_color)

inline val View.text_4_color get() = context.text_4_color
inline val Context.text_4_color: Int
    get() = ContextCompat.getColor(this, R.color.text_4_color)

inline val View.text_3_color get() = context.text_3_color
inline val Context.text_3_color: Int
    get() = ContextCompat.getColor(this, R.color.text_3_color)

inline val View.text_2_color get() = context.text_2_color
inline val Context.text_2_color: Int
    get() = ContextCompat.getColor(this, R.color.text_2_color)
inline val View.text_1_color get() = context.text_1_color
inline val Context.text_1_color: Int
    get() = ContextCompat.getColor(this, R.color.text_1_color)

/*--- Icon Color ---*/
inline val View.ui_icon_dynamic_active get() = context.ui_icon_dynamic_active
inline val Fragment.ui_icon_dynamic_active get() = requireContext().ui_icon_dynamic_active
inline val Context.ui_icon_dynamic_active: Int
    get() = ContextCompat.getColor(this, R.color.ui_icon_dynamic_active)

inline val View.ui_icon_dynamic_default get() = context.ui_icon_dynamic_default
inline val Fragment.ui_icon_dynamic_default get() = requireContext().ui_icon_dynamic_default
inline val Context.ui_icon_dynamic_default: Int
    get() = ContextCompat.getColor(this, R.color.ui_icon_dynamic_default)
inline val View.ui_icon_dynamic_error get() = context.ui_icon_dynamic_error
inline val Fragment.ui_icon_dynamic_error get() = requireContext().ui_icon_dynamic_error
inline val Context.ui_icon_dynamic_error: Int
    get() = ContextCompat.getColor(this, R.color.ui_icon_dynamic_error)
inline val View.ui_icon_dynamic_inactive get() = context.ui_icon_dynamic_inactive
inline val Fragment.ui_icon_dynamic_inactive get() = requireContext().ui_icon_dynamic_inactive
inline val Context.ui_icon_dynamic_inactive: Int
    get() = ContextCompat.getColor(this, R.color.ui_icon_dynamic_inactive)
inline val View.ui_icon_dynamic_warning get() = context.ui_icon_dynamic_warning
inline val Context.ui_icon_dynamic_warning: Int
    get() = ContextCompat.getColor(this, R.color.ui_icon_dynamic_warning)

/*--- General ---*/
inline val View.ui_static_white get() = context.ui_static_white
inline val Context.ui_static_white: Int
    get() = ContextCompat.getColor(this, R.color.white)
inline val View.shadow_blur_color get() = context.shadow_blur_color
inline val Context.shadow_blur_color: Int
    get() = ContextCompat.getColor(this, R.color.shadow_blur_color)

inline val View.ui_fill_active_primary get() = context.ui_fill_active_primary
inline val Fragment.ui_fill_active_primary get() = requireContext().ui_fill_active_primary
inline val Context.ui_fill_active_primary: Int
    get() = ContextCompat.getColor(this, R.color.ui_fill_active_primary)

inline val View.ui_fill_active_secondary get() = context.ui_fill_active_secondary
inline val Fragment.ui_fill_active_secondary get() = requireContext().ui_fill_active_secondary
inline val Context.ui_fill_active_secondary: Int
    get() = ContextCompat.getColor(this, R.color.ui_fill_active_secondary)

inline val View.ui_fill_error_primary get() = context.ui_fill_error_primary
inline val Fragment.ui_fill_error_primary get() = requireContext().ui_fill_error_primary
inline val Context.ui_fill_error_primary: Int
    get() = ContextCompat.getColor(this, R.color.ui_fill_error_primary)

inline val View.ui_fill_error_secondary get() = context.ui_fill_error_secondary
inline val Fragment.ui_fill_error_secondary get() = requireContext().ui_fill_error_secondary
inline val Context.ui_fill_error_secondary: Int
    get() = ContextCompat.getColor(this, R.color.ui_fill_error_secondary)

inline val View.ui_fill_mute_primary get() = context.ui_fill_mute_primary
inline val Fragment.ui_fill_mute_primary get() = requireContext().ui_fill_mute_primary
inline val Context.ui_fill_mute_primary: Int
    get() = ContextCompat.getColor(this, R.color.ui_fill_mute_primary)

inline val View.ui_fill_mute_secondary get() = context.ui_fill_mute_secondary
inline val Fragment.ui_fill_mute_secondary get() = requireContext().ui_fill_mute_secondary
inline val Context.ui_fill_mute_secondary: Int
    get() = ContextCompat.getColor(this, R.color.ui_fill_mute_secondary)

inline val View.input_border_not_focus get() = context.input_border_not_focus
inline val Context.input_border_not_focus: Int
    get() = ContextCompat.getColor(this, R.color.input_border_not_focus)

inline val View.input_border_focus get() = context.input_border_focus
inline val Context.input_border_focus: Int
    get() = ContextCompat.getColor(this, R.color.input_border_focus)

inline val View.input_focus_background get() = context.input_focus_background
inline val Context.input_focus_background: Int
    get() = ContextCompat.getColor(this, R.color.input_focus_background)

inline val View.ui_fill_background_tertiary get() = context.ui_fill_background_tertiary
inline val Fragment.ui_fill_background_tertiary get() = requireContext().ui_fill_background_tertiary
inline val Context.ui_fill_background_tertiary: Int
    get() = ContextCompat.getColor(this, R.color.ui_fill_background_tertiary)

inline val View.fill_4_color get() = context.fill_4_color
inline val Fragment.fill_4_color get() = requireContext().fill_4_color
inline val Context.fill_4_color: Int
    get() = ContextCompat.getColor(this, R.color.fill_4_color)

inline val View.fill_3_color get() = context.fill_3_color
inline val Fragment.fill_3_color get() = requireContext().fill_3_color
inline val Context.fill_3_color: Int
    get() = ContextCompat.getColor(this, R.color.fill_3_color)

inline val View.fill_2_color get() = context.fill_2_color
inline val Fragment.fill_2_color get() = requireContext().fill_2_color
inline val Context.fill_2_color: Int
    get() = ContextCompat.getColor(this, R.color.fill_2_color)

inline val View.fill_1_color get() = context.fill_1_color
inline val Fragment.fill_1_color get() = requireContext().fill_1_color
inline val Context.fill_1_color: Int
    get() = ContextCompat.getColor(this, R.color.fill_1_color)


