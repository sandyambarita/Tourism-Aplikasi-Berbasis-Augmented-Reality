<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel frontend\models\TQuestionSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Question';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tquestion-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Question', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
      //  'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

           // 'question_id',
           // 'challenge_type_id',
            [
                'attribute' => 'Challenge Type Name',
                'format' => 'raw',
                'value' => function ($model) {
                    $typename = \frontend\models\TChallengeType::find()->where(['challenge_type_id'=>$model->challenge_type_id])->one();
                    return $typename->type_name;
                },
            ],
            [
                'attribute' => 'Checkpoint',
                'format' => 'raw',
                'value' => function ($model) {
                    $checkpoint_name = \frontend\models\TCheckpoint::find()->where(['checkpoint_id'=>$model->checkpoint_id])->one();
                    return $checkpoint_name->checkpoint_name;
                },
            ],
            'question',
            'no_soal',
            'answer',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
