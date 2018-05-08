<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel frontend\models\TCheckpointSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Checkpoint';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tcheckpoint-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Checkpoint', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
       // 'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            //'checkpoint_id',
           // 'location_id',

            [
                'attribute' => 'Location',
                'format' => 'raw',
                'value' => function ($model) {
                    $location = \frontend\models\TLocation::find()->where(['location_id'=>$model->location_id])->one();
                    return $location->location_name;
                },
            ],
            'checkpoint_name',
            'latitude',
            'longitude',
            'path_gambarpoint',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
