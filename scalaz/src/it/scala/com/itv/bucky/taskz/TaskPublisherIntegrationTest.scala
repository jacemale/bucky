package com.itv.bucky.taskz

import com.itv.bucky._
import com.itv.bucky.suite.{PublisherIntegrationTest, RequeueStrategy, TestFixture}

import org.scalatest.concurrent.Eventually

import scalaz.concurrent.Task
import scala.concurrent.duration._

class TaskPublisherIntegrationTest extends PublisherIntegrationTest[Task, Throwable] with TaskEffectVerification {
  implicit val eventuallyPatienceConfig = Eventually.PatienceConfig(1.seconds, 100.millis)

  override def withPublisherAndConsumer(queueName: QueueName, requeueStrategy: RequeueStrategy[Task])(
      f: (TestFixture[Task]) => Unit): Unit =
    IntegrationUtils.withPublisherAndConsumer(queueName, requeueStrategy)(f)

  override implicit def effectMonad: MonadError[Task, Throwable] = TaskExt.taskMonad

}
