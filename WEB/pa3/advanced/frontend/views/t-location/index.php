<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel frontend\models\TLocationSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Location';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tlocation-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Location', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
       // 'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            //'location_id',
            'location_name',
            'city_name',
            'path_gambar',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
