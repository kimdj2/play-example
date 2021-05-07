package controllers.tweet

import javax.inject.{Inject, Singleton}
import play.api.mvc.ControllerComponents
import play.api.mvc.BaseController
import play.api.mvc.Request
import play.api.mvc.AnyContent
import models.Tweet

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def list() = Action { implicit request: Request[AnyContent] =>
    // 1から10までのTweetクラスのインタンスを作成しています。
    // 1 to 10だとIntになってしまうので1L to 10LでLongにしています。
    val tweets: Seq[Tweet] = (1L to 10L).map(i => Tweet(Some(i), s"test tweet${i.toString}"))

    // viewの引数としてtweetsを渡します。
    Ok(views.html.tweet.list(tweets))
  }

}