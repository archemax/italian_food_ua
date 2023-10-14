import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.italianfoodukraine.R
import com.example.italianfoodukraine.model.RecipeModel
import com.example.italianfoodukraine.user_interface.OneRecipeItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDisplayHorizontalLayout(
    configurationOfScreen: Configuration,
    bigListOfRecipesState: StateFlow<List<RecipeModel>>,
    categoriesOfFood: List<String>,
    selectedCategory: MutableState<String>,
    queryState: MutableState<String>,
    toOneRecipeScreen: (String) -> Unit,
    finalFilteredList: List<RecipeModel>,
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {

        Column() {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.whatShallWeCook),
                style = MaterialTheme.typography.headlineLarge
            )

            Column(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            categoriesOfFood.forEach { category ->
                val isSelected = category == selectedCategory.value
                val myElevation: SelectableChipElevation = if (isSelected) {
                    FilterChipDefaults.filterChipElevation(8.dp)
                } else {
                    FilterChipDefaults.filterChipElevation(0.dp)
                }

                ElevatedFilterChip(
                    selected = isSelected,
                    onClick = {
                        selectedCategory.value = if (isSelected) "" else category
                    },
                    elevation = myElevation,
                    label = {
                        Text(
                            text = category,
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF3F486C),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.1.sp,
                            )
                        )
                    },
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = Color(0xFF848EB2),
                        selectedBorderColor = Color(0xFF848EB2), selectedBorderWidth = 1.dp
                    ),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFECEFFB)
                    ),
                )
                Spacer(modifier = Modifier.padding(end = 4.dp))
            }
        }
    }

////Recommended for you//////////////////////////////////////////////////////////////////////////////////

        Text(
            text = stringResource(R.string.Recommendations),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
//////Recommended for you//////////////////////////////////////////////////////////////////////////////////


//////LAZY COLUMN//////////////////////////////////////////////////////////////////////////////////////////////////
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.,
            ) {
                items(
                    if (selectedCategory.value.isNotEmpty() || queryState.value.isNotEmpty()) {
                        finalFilteredList
                    } else {
                        bigListOfRecipesState.value
                    }
                ) { recipe ->
                    OneRecipeItem(
                        oneRecipe = recipe,
                        onClick = {
                            toOneRecipeScreen(recipe.id.toString())
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview (showSystemUi = true,
        device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 240)
@Composable
fun PreviewMainDisplayHorizontalLayout() {
    val configuration = Configuration() // You may provide your desired configuration
    val bigListOfRecipes =
        MutableStateFlow<List<RecipeModel>>(emptyList()) // Replace with your list of recipes
    val categories = listOf("Category1", "Category2") // Replace with your categories
    val selectedCategory =
        remember { mutableStateOf("Category1") } // Replace with your selected category
    val queryState = remember { mutableStateOf("") } // Replace with your query state
    val finalFilteredList = emptyList<RecipeModel>()
    val toOneRecipeString : (String) -> Unit = {

    }
    MainDisplayHorizontalLayout(
        configurationOfScreen = configuration,
        bigListOfRecipesState = bigListOfRecipes,
        categoriesOfFood = categories,
        selectedCategory = selectedCategory,
        queryState = queryState,
        finalFilteredList = finalFilteredList,
        toOneRecipeScreen = toOneRecipeString


    )

}

