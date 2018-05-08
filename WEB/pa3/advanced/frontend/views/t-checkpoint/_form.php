<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use frontend\models\TLocation;
use kartik\select2\Select2;

/* @var $this yii\web\View */
/* @var $model frontend\models\TCheckpoint */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="tcheckpoint-form">

    <?php $form = ActiveForm::begin(); ?>

    <!-- <?= $form->field($model, 'location_id')->textInput() ?> -->
    <!--?= $form->field($model, 'location_id')->dropDownList(ArrayHelper::map(TLocation::find()->all(),'location_id', 'location_name'),['prompt'=>'-Chose Location-'])?-->

    <?= $form->field($model, 'location_id')->widget(Select2::classname(), [
        'data' => ArrayHelper::map(TLocation::find()->all(),'location_id', 'location_name'),
        'language' => 'en',
        'options' => ['placeholder' => '-Chose Location-'],
        'pluginOptions' => [
            'allowClear' => true
        ],
    ]);?>

    <?= $form->field($model, 'checkpoint_name')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'latitude')->textInput() ?>

    <?= $form->field($model, 'longitude')->textInput() ?>

    <?= $form->field($model, 'path_gambarpoint')->fileinput() ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
