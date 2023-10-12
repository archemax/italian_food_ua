package com.example.italianfoodukraine.user_interface.Displays.main_display

import androidx.lifecycle.ViewModel
import com.example.italianfoodukraine.list_of_food.BigListOfFood
import com.example.italianfoodukraine.model.RecipeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel

class MainDisplayViewModel @Inject constructor() : ViewModel() {
    private val myViewModelRepo = BigListOfFood
    private val _state = MutableStateFlow<List<RecipeModel>>(emptyList())
    val state: StateFlow<List<RecipeModel>> = _state

    init {
        _state.value = myViewModelRepo.recipes
    }

    fun myFilter(query: String, category: String): List<RecipeModel> {
        val filteredRecipes = _state.value.filter { oneRecipe ->
            oneRecipe.categoryOfFood.contains(category, ignoreCase = true)
                    &&
                    (oneRecipe.dishTitle.contains(query, ignoreCase = true)
                            || oneRecipe.description.contains(query, ignoreCase = true))
        }
        return filteredRecipes
    }

}

