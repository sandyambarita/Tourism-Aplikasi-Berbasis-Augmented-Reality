<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model frontend\models\TCheckpoint */

$this->title = $model->checkpoint_id;
$this->params['breadcrumbs'][] = ['label' => 'Checkpoint', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tcheckpoint-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->checkpoint_id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->checkpoint_id], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'checkpoint_id',
            'location_id',
            'checkpoint_name',
            'latitude',
            'longitude',
            'path_gambarpoint',
        ],
    ]) ?>

</div>
