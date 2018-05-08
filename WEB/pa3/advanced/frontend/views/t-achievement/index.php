<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel frontend\models\TAchievementSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Achievement';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tachievement-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Achievement', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        //'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            //'achievement_id',
            //'challenge_type_id',
            [
                'attribute' => 'Challenge Type',
                'format' => 'raw',
                'value' => function ($model) {
                    $location = \frontend\models\TChallengeType::find()->where(['challenge_type_id'=>$model->challenge_type_id])->one();
                    return $location->type_name;
                },
            ],
            //'checkpoint_id',
            [
                'attribute' => 'Checkpoint',
                'format' => 'raw',
                'value' => function ($model) {
                    $location = \frontend\models\TCheckpoint::find()->where(['checkpoint_id'=>$model->checkpoint_id])->one();
                    return $location->checkpoint_name;
                },
            ],
            'hadiah',
            'path_gambar',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
