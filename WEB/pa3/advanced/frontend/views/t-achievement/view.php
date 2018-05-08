<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model frontend\models\TAchievement */

$this->title = $model->achievement_id;
$this->params['breadcrumbs'][] = ['label' => 'Achievement', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tachievement-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->achievement_id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->achievement_id], [
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
            'achievement_id',
            'challenge_type_id',
            'checkpoint_id',
            'hadiah',
            'path_gambar',
        ],
    ]) ?>

</div>
