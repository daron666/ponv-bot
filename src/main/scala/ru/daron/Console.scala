package ru.daron

import cats.effect.Sync
import com.typesafe.scalalogging.{Logger, StrictLogging}

import scala.io.StdIn

trait Console[F[_]] {
  def read: F[String]

  def print(s: String): F[Unit]
}

object Console {

  def apply[F[_]: Sync]: Console[F] = new Console[F] with StrictLogging {
    override def read: F[String] = Sync[F].delay(StdIn.readLine())

    override def print(s: String): F[Unit] = Sync[F].delay(logger.info(s))
  }
}
