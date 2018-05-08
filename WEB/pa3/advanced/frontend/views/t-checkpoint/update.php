<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model frontend\models\TCheckpoint */

$this->title = 'Update Checkpoint: ' . $model->checkpoint_id;
$this->params['breadcrumbs'][] = ['label' => 'Checkpoint', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->checkpoint_id, 'url' => ['view', 'id' => $model->checkpoint_id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="tcheckpoint-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
