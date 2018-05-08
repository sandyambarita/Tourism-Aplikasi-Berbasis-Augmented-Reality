<?php

namespace frontend\controllers;

use Yii;
use frontend\models\TCheckpoint;
use frontend\models\TCheckpointSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * TCheckpointController implements the CRUD actions for TCheckpoint model.
 */
class TCheckpointController extends Controller
{
    /**
     * @inheritdoc
     */
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['POST'],
                ],
            ],
        ];
    }

    /**
     * Lists all TCheckpoint models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new TCheckpointSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single TCheckpoint model.
     * @param integer $id
     * @return mixed
     */
    public function actionView($id)
    {
        return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
    }

    /**
     * Creates a new TCheckpoint model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new TCheckpoint();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            try{
               $picture = \yii\web\UploadedFile::getInstance($model, 'path_gambarpoint');
               $model->path_gambarpoint= $model->checkpoint_name.'.'.$picture->extension;
               if($model->save()){
                   $picture->saveAs('../../../../pa3ws/digitour/image/'.$model->checkpoint_name.'.'.$picture->extension);
                   Yii::$app->getSession()->setFlash('Success','Data saved!');
                   return $this->redirect(['index','id'=>$model->checkpoint_id]);
               }else{
                   Yii::$app->getSession()->setFlash('Error','Data not saved!');
                   return $this->render('create', [
                         'model' => $model,
                   ]);
               }
          }catch(Exception $e){
              Yii::$app->getSession()->setFlash('error',"{$e->getMessage()}");
          }
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing TCheckpoint model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->checkpoint_id]);
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing TCheckpoint model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $id
     * @return mixed
     */
    public function actionDelete($id)
    {
        $this->findModel($id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the TCheckpoint model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @return TCheckpoint the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = TCheckpoint::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
}
