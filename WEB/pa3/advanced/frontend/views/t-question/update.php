<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model frontend\models\TQuestion */

$this->title = 'Update Question: ' . $model->question_id;
$this->params['breadcrumbs'][] = ['label' => 'Question', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->question_id, 'url' => ['view', 'id' => $model->question_id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="tquestion-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
