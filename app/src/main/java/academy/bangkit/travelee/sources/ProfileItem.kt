package academy.bangkit.travelee.sources

import academy.bangkit.travelee.R
import academy.bangkit.travelee.model.ProfileItemModel

object ProfileItem {
    val data = listOf<ProfileItemModel>(
        ProfileItemModel(
            Id = 1,
            Icon = R.drawable.outline_account_circle_24,
            Label = "Edit Profile",
            Route = "/"
        ),
        ProfileItemModel(
            Id = 2,
            Icon = R.drawable.outline_notifications_24,
            Label = "Notifications",
            Route = "/"
        ),
        ProfileItemModel(
            Id = 3,
            Icon = R.drawable.outline_question_answer_24,
            Label = "FAQ",
            Route = "/"
        ),
        ProfileItemModel(
            Id = 4,
            Icon = R.drawable.outline_toggle_off_24,
            Label = "Dark Mode",
            Route = "/"
        ),
        ProfileItemModel(
            Id = 5,
            Icon = R.drawable.outline_language_24,
            Label = "Language",
            Route = "/"
        ),
        ProfileItemModel(
            Id = 6,
            Icon = R.drawable.baseline_share_24,
            Label = "Invite Friends",
            Route = "/"
        )
    )
}