package com.example.italianfoodukraine.user_interface.Displays

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.imaukr.R

import com.imaukr.italianfoodukraine.model.RecipeModel

import com.imaukr.italianfoodukraine.user_interface.Displays.one_recipe_display.OneRecipeDispalyViewModel
import com.imaukr.italianfoodukraine.user_interface.Displays.one_recipe_display.OneRecipeDisplayHorizontal

@SuppressLint("SuspiciousIndentation")
@Composable
fun MyOneRecipeDisplay(
    configurationOfScreen: Configuration,
    navController: NavController,
    routeId: String
) {
    val oneRecipeScreenViewModel: OneRecipeDispalyViewModel = hiltViewModel()
    val id = routeId.toIntOrNull()
    val recipe =
        id?.let { oneRecipeScreenViewModel.getOneRecipe(it) }

    when (configurationOfScreen.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            OneRecipeDisplayVertical(
                routeId = routeId,
                recipe = recipe,
                navController = navController,
            )

        }

        Configuration.ORIENTATION_LANDSCAPE -> {
            OneRecipeDisplayHorizontal(
                routeId = routeId,
                recipe = recipe,
                navController = navController,
            )
        }
    }


}

/////////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun OneRecipeDisplayVertical(
    recipe: RecipeModel?,
    routeId: String,
    navController: NavController,


    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
            .background(Color.White)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.BottomCenter

        ) {
            if (recipe != null)
                Image(
                    // image of the recipe
                    modifier = Modifier
                        //.size(450.dp)
                        .clip(RoundedCornerShape(0.dp))
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = recipe.imageResId),
                    contentDescription = "recipe Image",
                )
            Box(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Image(painter = painterResource(id = R.drawable.arrow_narrow_left),
                    contentDescription = "arrow back",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .padding(16.dp))
            }

            Row(
                // row with the title of dish
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center,

                ) {
                if (recipe != null)
                    Text(
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp),
                        text = recipe.dishTitle,
                        maxLines = 2,
                        style = MaterialTheme.typography.titleLarge
                    )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 0.dp, end = 0.dp)
                .verticalScroll(rememberScrollState())
                .background(color = Color.White),

            ) {

/////////////////////recipe info///////////////////////////////////////////////////////

            val showMore = remember { mutableStateOf(false) }
            val text = "${recipe?.description}"

            Column(modifier = Modifier.padding(start = 28.dp, end = 28.dp)) {
                //MyCategoryBadge(category = "${recipe?.category}")

                Column(modifier = Modifier
                    .animateContentSize(animationSpec = tween(100))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { showMore.value = !showMore.value }) {
                    if (showMore.value) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodySmall
                        )
                    } else {
                        Text(
                            text = text,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            ///////////////////////////////////////////////////////////
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.padding(start = 28.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Інгредієнти",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                val editedIgredients = recipe?.ingredients?.toMutableList()
                val dropFirstElement = editedIgredients?.drop(1)?.joinToString()

                if (recipe != null)
                    Text(
                        text = "$dropFirstElement",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF444444),
                            letterSpacing = 0.8.sp,
                        )
                    )
            }
            Spacer(modifier = Modifier.size(16.dp))


            ///////Step by step guide/////////////////////////////////////////////////////////////////////////////////////
            Column(
                modifier = Modifier.padding(start = 28.dp, end = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Процес приготування",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.headlineMedium
                )

                val mutableListRecipe = recipe?.instructions?.toMutableList()
                val firstElementRemoved = mutableListRecipe?.drop(1)?.joinToString()
                if (recipe != null)
                    Text(
                        text = "${firstElementRemoved}",
                        modifier = Modifier.padding(start = 0.dp, end = 0.dp),
                        style = MaterialTheme.typography.bodySmall,
                    )
            }
            Spacer(modifier = Modifier.weight(0.5f))
//////////////////////CATEGORY///////////////////////////////////////////////
            Column(
                modifier = Modifier.padding(start = 28.dp, top = 24.dp, bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.category_icon),
                        contentDescription = null,
                        tint = Color(0xFF3F486C)
                    )
                    Text(
                        text = "  ${recipe?.categoryOfFood}",
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = 8.sp,
                            lineHeight = 16.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF3F486C),
                            letterSpacing = 0.4.sp,
                        )
                    )
                }
            }

        }
    }
}










