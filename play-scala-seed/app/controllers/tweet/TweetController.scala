package controllers.tweet

import javax.inject.{Inject, Singleton}
import play.api.mvc.ControllerComponents
import play.api.mvc.BaseController
import play.api.mvc.Request
import play.api.mvc.AnyContent
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport

import models.Tweet

case class TweetFormData(content: String)

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with I18nSupport {
  // DBのMockとして利用したいので、先ほどlistに作成したインスタンスをフィールドとして定義し直す
  val tweets: Seq[Tweet] = (1L to 10L).map(i => Tweet(Some(i), s"test tweet${i.toString}"))

  def list() = Action { implicit request: Request[AnyContent] =>
    // viewの引数としてtweetsを渡します。
    Ok(views.html.tweet.list(tweets))
  }

  def show(id: Long) = Action { implicit request: Request[AnyContent] =>
    // idが存在して、値が一致する場合にfindが成立
    tweets.find(_.id.exists(_ == id)) match {
      case Some(tweet) => Ok(views.html.tweet.show(tweet))
      // status codeを404にしつつページを返しています。
      case None        => NotFound(views.html.error.page404())
    }
  }

  val form = Form(
      // html formのnameがcontentのものを140文字以下の必須文字列に設定する
      mapping(
        "content" -> nonEmptyText(maxLength = 140)
      )(TweetFormData.apply)(TweetFormData.unapply)
    )

  def register() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tweet.store(form))
  }

  // コンパイルエラー回避用に何もしない登録用のstoreメソッドも作成
  def store() = Action { implicit request: Request[AnyContent] =>
    NoContent
  }


}