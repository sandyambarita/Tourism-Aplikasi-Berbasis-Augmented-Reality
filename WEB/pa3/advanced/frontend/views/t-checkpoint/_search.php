<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model frontend\models\TCheckpointSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="tcheckpoint-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>
<!-- 
    <?  // $form->field($model, 'checkpoint_id') ?>
 -->
    <?= $form->field($model, 'location_id') ?>

    <?= $form->field($model, 'checkpoint_name') ?>

    <?= $form->field($model, 'latitude') ?>

    <?= $form->field($model, 'longitude') ?>

    <?= $form->field($model, 'path_gambarpoint') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
