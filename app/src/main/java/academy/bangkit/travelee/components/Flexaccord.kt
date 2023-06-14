package academy.bangkit.travelee.components

import academy.bangkit.travelee.model.ContentModel
import academy.bangkit.travelee.ui.theme.Dark
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Accordion(itemModel: ContentModel, onClickItem: () -> Unit, expanded: Boolean) {
    Card(colors = CardDefaults.cardColors(containerColor = White)) {
        Column {
            HeaderView(
                title = itemModel.Title,
                onClickItem = onClickItem,
                isExpanded = expanded
            )
            ExpandableView(
                answerText = itemModel.Content,
                isExpanded = expanded)
        }
    }
}

@Composable
fun HeaderView(title: String, onClickItem: () -> Unit, isExpanded: Boolean) {
    Box(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClickItem
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = Dark,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = "Expand",
                modifier = Modifier.width(14.dp),
                tint = Dark
            )
        }
    }
}

@Composable
fun ExpandableView(answerText: String, isExpanded: Boolean) {
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }

    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = answerText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = Dark
                        )
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun AccordionPreview() {
    Accordion(
        itemModel = ContentModel(
            Id = 1,
            Title = "Pahami dan Hormati Budaya Lokal",
            Content = """
                Belajar tentang budaya, adat istiadat, dan tradisi masyarakat lokal sebelum berkunjung.
                Hormati kebiasaan dan aturan komunikasi yang berlaku di desa wisata.
                Hindari perilaku yang dianggap mengganggu atau merugikan budaya lokal, seperti fotografi yang tidak pantas atau mengambil barang-barang tanpa izin.
            """.trimIndent()
        ),
        onClickItem = {},
        expanded = true
    )
}
@Preview
@Composable
fun AccordionPreview2() {
    Accordion(
        itemModel = ContentModel(
            Id = 1,
            Title = "Pahami dan Hormati Budaya Lokal",
            Content = """
                Belajar tentang budaya, adat istiadat, dan tradisi masyarakat lokal sebelum berkunjung.
                Hormati kebiasaan dan aturan komunikasi yang berlaku di desa wisata.
                Hindari perilaku yang dianggap mengganggu atau merugikan budaya lokal, seperti fotografi yang tidak pantas atau mengambil barang-barang tanpa izin.
            """.trimIndent()
        ),
        onClickItem = {},
        expanded = false
    )
}
