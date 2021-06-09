// Taskに登録したものと同様にpackageを指定
package tasks

import com.typesafe.config.ConfigFactory
import slick.codegen.SourceCodeGenerator

object SlickCodeGen extends App {
  // typesafe configを利用してapplication.confをロード
  val config      = ConfigFactory.load()
  val defaultPath = "slick.dbs.default"

  // 末尾の$を削除
  // s補間子つき文字列では${}. $hogeで変数を参照可能です。この場合 slick.dbs.default.profileのように展開されます。
  val profile   = config.getString(s"$defaultPath.profile").dropRight(1)
  val driver    = config.getString(s"$defaultPath.db.driver")
  val url       = config.getString(s"$defaultPath.db.url")
  val user      = config.getString(s"$defaultPath.db.user")
  val password  = config.getString(s"$defaultPath.db.password")

  // pathが別なので直接呼び出し
  val outputDir = config.getString("slick.codegen.outputDir")
  val pkg       = config.getString("application.package")

  // slick-codegenを実行
  SourceCodeGenerator.main(
    Array(profile, driver, url, outputDir, pkg, user, password)
  )
}