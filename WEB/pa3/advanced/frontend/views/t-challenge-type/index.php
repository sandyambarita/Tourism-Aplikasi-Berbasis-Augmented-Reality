<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel frontend\models\TChallengeTypeSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Challenge Type';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tchallenge-type-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Challenge Type', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        //'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            // 'challenge_type_id',
          //  'checkpoint_id',
            'type_name',
            'description',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?> 
</div>
