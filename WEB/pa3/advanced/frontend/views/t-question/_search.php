<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model frontend\models\TQuestionSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="tquestion-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= //$form->field($model, 'question_id') ?>

    <?= $form->field($model, 'checkpoint_id') ?>

    <?= $form->field($model, 'challenge_type_id') ?>

    <?= $form->field($model, 'location_id') ?>

    <?= $form->field($model, 'question') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
