<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model frontend\models\TChallengeType */

$this->title = $model->challenge_type_id;
$this->params['breadcrumbs'][] = ['label' => 'Challenge Type', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tchallenge-type-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->challenge_type_id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->challenge_type_id], [
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
            'challenge_type_id',
            'type_name',
            'description',
        ],
    ]) ?>

</div>
