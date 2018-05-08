<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use frontend\models\TLocation;
use frontend\models\TCheckpoint;
use frontend\models\TChallengeType;
use kartik\select2\Select2;

/* @var $this yii\web\View */
/* @var $model frontend\models\TQuestion */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="tquestion-form">

    <?php $form = ActiveForm::begin(); ?>

    <!--?= $form->field($model, 'challenge_type_id')->textInput() ?-->
     <!--?= $form->field($model, 'challenge_type_id')->dropDownList(ArrayHelper::map(TChallengeType::find()->all(),'challenge_type_id', 'type_name'),['prompt'=>'-Chose Challenge Type-'])?-->
     <?= $form->field($model, 'challenge_type_id')->widget(Select2::classname(), [
        'data' => ArrayHelper::map(TChallengeType::find()->all(),'challenge_type_id', 'type_name'),
        'language' => 'en',
        'options' => ['placeholder' => '-Chose Challenge Type-'],
        'pluginOptions' => [
            'allowClear' => true
        ],
    ]);?>

     <?= $form->field($model, 'checkpoint_id')->widget(Select2::classname(), [
        'data' => ArrayHelper::map(TCheckpoint::find()->all(),'checkpoint_id', 'checkpoint_name'),
        'language' => 'en',
        'options' => ['placeholder' => '-Chose Checkpoint-'],
        'pluginOptions' => [
            'allowClear' => true
        ],
    ]);?>

    <?= $form->field($model, 'question')->textArea() ?>

    <!--?= $form->field($model, 'no_soal')->textInput() ?-->

    <?= $form->field($model, 'option1')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'option2')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'option3')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'option4')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'answer')->textInput(['maxlength' => true]) ?>


    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
