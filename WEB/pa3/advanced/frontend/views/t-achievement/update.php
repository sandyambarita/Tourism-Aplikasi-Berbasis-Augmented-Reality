<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model frontend\models\TAchievement */

$this->title = 'Update Achievement: ' . $model->achievement_id;
$this->params['breadcrumbs'][] = ['label' => 'Achievement', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->achievement_id, 'url' => ['view', 'id' => $model->achievement_id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="tachievement-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
