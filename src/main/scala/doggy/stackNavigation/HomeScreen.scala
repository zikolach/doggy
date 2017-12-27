package doggy.stackNavigation

import doggy.stackNavigation.ScreenWithParams.Params
import sri.macros.OptionalParam._
import sri.macros.{OptDefault => NoValue, OptionalParam => U}
import sri.navigation._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal

import scala.scalajs.js

class HomeScreen extends NavigationScreenComponentNoPS {

  import HomeScreen._

  def render() = {
    View(style = styles.container)(
      getBlock(() =>
        navigation.navigate[ScreenWithParams](new Params {
          override val title: String = "Second title"
        }), "Home"),
      getBlock(() => navigation.navigate[ScreenWithCustomRightButton], "Map"),
      getBlock(() => navigation.navigate[LazyLoadScreen], "Cloud"),
      getBlock(() => navigation.navigate[AboutScreen], "Password")
    )
  }

  @inline
  def getBlock(onPress: () => _, title: String) =
    TouchableOpacity(
      style = styles.block,
      onPress = onPress,
      activeOpacity = 0.8
    )(
      Text(style = styles.blockText)(title),
      Image(
        source = ImageSource(s"https://png.icons8.com/ultraviolet/100/${title.toLowerCase}.png"),
        style = styles.icon
      )
    )
}

object HomeScreen {

  object styles extends InlineStyleSheetUniversal {

    import dsl._

    val container = style(
      flex := 1,
      padding := 20,
      flexDirection.row,
      flexWrap.wrap
    )

    val block = style(
      width := "42%",
      height := 140,
      backgroundColor := "white",
      borderColor := "#eee",
      borderRadius := 2,
      margin := 10,
      paddingHorizontal := 3,
      shadowColor := "grey",
      shadowOpacity := 0.5,
      shadowRadius := 2,
      shadowOffset := js.Dynamic.literal(height = 1, width = 0),
      elevation := 3,
      borderWidth := 1,
      justifyContent.center,
      alignItems.center
    )

    val blockText = style(
      fontWeight := "500",
      fontSize := 14
    )

    val icon = style(
      width := 100,
      height := 100
    )
  }

}
